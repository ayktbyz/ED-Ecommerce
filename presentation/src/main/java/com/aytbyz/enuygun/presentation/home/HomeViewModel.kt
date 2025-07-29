package com.aytbyz.enuygun.presentation.home

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.usecase.AddFavoriteUseCase
import com.aytbyz.enuygun.domain.usecase.GetAllFavoritesUseCase
import com.aytbyz.enuygun.domain.usecase.GetProductsUseCase
import com.aytbyz.enuygun.domain.usecase.IsFavoriteUseCase
import com.aytbyz.enuygun.domain.usecase.RemoveFavoriteUseCase
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.screen.BaseViewModel
import com.aytbyz.enuygun.presentation.base.screen.model.BaseUIEvent
import com.aytbyz.enuygun.presentation.components.bottomsheet.model.ProductFilterUIModel
import com.aytbyz.enuygun.presentation.home.intent.HomeIntent
import com.aytbyz.enuygun.presentation.home.model.HomeUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProducts: GetProductsUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState: StateFlow<HomeUIState> = _uiState

    private val _filter = MutableStateFlow(ProductFilterUIModel())
    val filter: StateFlow<ProductFilterUIModel> = _filter

    init {
        fetchProducts()
    }

    fun fetchProducts(loadMore: Boolean = false) {
        val state = _uiState.value

        if (state.isLoadingMore || (!state.canLoadMore && loadMore)) {
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMore = true) }

            val skip = if (loadMore) state.currentSkip + state.limit else 0
            val limit = state.limit

            getProducts(
                query = state.searchQuery,
                sortBy = state.selectedSortOption?.field?.key,
                sortDirection = state.selectedSortOption?.direction?.key,
                skip = skip,
                limit = limit
            ).collectLatest { page ->
                val favoriteList = getAllFavoritesUseCase().first()

                val productsWithFavoriteFlag = page.products.map { product ->
                    product.copy(isFavorite = favoriteList.any { it.id == product.id })
                }

                val mergedAllProducts: List<Product> =
                    if (loadMore) _uiState.value.products + productsWithFavoriteFlag
                    else productsWithFavoriteFlag

                val finalVisibleProducts = if (isFilterActive()) {
                    applyLocalFilter(mergedAllProducts)
                } else {
                    mergedAllProducts
                }

                _uiState.update {
                    it.copy(
                        products = finalVisibleProducts,
                        total = page.total,
                        isLoadingMore = false,
                        canLoadMore = skip + limit < page.total,
                        currentSkip = skip
                    )
                }
            }
        }
    }

    private fun toggleFavorite(product: Product) {
        viewModelScope.launch {
            val isFav = isFavoriteUseCase(product.id)

            if (isFav) {
                removeFavoriteUseCase(product)
                sendUiEvent(
                    BaseUIEvent.ShowToast(
                        context.getString(R.string.removed_from_favorites)
                    )
                )
            } else {
                addFavoriteUseCase(product)
                sendUiEvent(
                    BaseUIEvent.ShowToast(
                        context.getString(R.string.added_to_favorites)
                    )
                )
            }

            _uiState.update { currentState ->
                currentState.copy(
                    products = currentState.products.map {
                        if (it.id == product.id) it.copy(isFavorite = !isFav) else it
                    })
            }
        }
    }

    fun refreshFavorites() {
        viewModelScope.launch {
            val favorites = getAllFavoritesUseCase().first()
            _uiState.update { state ->
                state.copy(
                    products = state.products.map { product ->
                        product.copy(isFavorite = favorites.any { it.id == product.id })
                    }
                )
            }
        }
    }

    fun setFilter(filter: ProductFilterUIModel) {
        _filter.value = filter
        _uiState.update { it.copy(products = applyLocalFilter(it.products)) }
    }

    fun clearFilter() {
        _filter.value = ProductFilterUIModel()
        fetchProducts()
    }

    fun dismissSortBottomSheet() {
        _uiState.value = _uiState.value.copy(showSortBottomSheet = false)
    }

    fun dismissFilterBottomSheet() {
        _uiState.value = _uiState.value.copy(showFilterBottomSheet = false)
    }

    private fun applyLocalFilter(allProducts: List<Product>): List<Product> {
        val f = _filter.value
        return allProducts.filter {
            it.price.toFloat() in f.selectedPriceRange &&
                    it.rating >= f.minRating &&
                    (!f.onlyFavorites || it.isFavorite)
        }
    }

    private fun isFilterActive(): Boolean {
        val filter = _filter.value
        return filter.selectedPriceRange.start > filter.minPrice ||
                filter.selectedPriceRange.endInclusive < filter.maxPrice ||
                filter.minRating > 0f ||
                filter.onlyFavorites
    }

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnSortClick -> {
                _uiState.value = _uiState.value.copy(showSortBottomSheet = true)
            }

            is HomeIntent.OnFilterClick -> {
                _uiState.value = _uiState.value.copy(showFilterBottomSheet = true)
            }

            is HomeIntent.OnSearchChange -> {
                _uiState.update { it.copy(searchQuery = intent.query) }
                fetchProducts()
            }

            is HomeIntent.OnSortSelected -> {
                _uiState.update {
                    it.copy(
                        selectedSortOption = intent.option, showSortBottomSheet = false
                    )
                }
                fetchProducts()
            }

            is HomeIntent.OnFavoriteToggle -> {
                toggleFavorite(intent.product)
            }

            is HomeIntent.LoadMore -> {
                fetchProducts(loadMore = true)
            }

            is HomeIntent.OnApplyFilter -> {
                setFilter(intent.filter)
                dismissFilterBottomSheet()
            }

            is HomeIntent.OnClearFilter -> {
                clearFilter()
                dismissFilterBottomSheet()
            }

            is HomeIntent.OnDismissFilterBottomSheet -> {
                dismissFilterBottomSheet()
            }

            is HomeIntent.OnDismissSortBottomSheet -> {
                dismissSortBottomSheet()
            }
        }
    }
}