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

    init {
        fetchProducts()
    }

    fun fetchProducts(loadMore: Boolean = false) {
        val state = _uiState.value

        if (state.isLoadingMore || (!state.canLoadMore && loadMore)) {
            println("🟡 Yükleme engellendi - isLoadingMore=${state.isLoadingMore}, canLoadMore=${state.canLoadMore}, loadMore=$loadMore")
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoadingMore = true) }

            val skip = if (loadMore) state.currentSkip + state.limit else 0
            val limit = state.limit

            println("➡️ fetchProducts çağrıldı → loadMore=$loadMore, skip=$skip, limit=$limit")


            getProducts(
                query = state.searchQuery,
                sortBy = state.selectedSortOption?.field?.key,
                sortDirection = state.selectedSortOption?.direction?.key,
                skip = skip,
                limit = limit
            ).collectLatest { page ->
                val favoriteList = getAllFavoritesUseCase().first()
                val updatedProducts = page.products.map { product ->
                    product.copy(isFavorite = favoriteList.any { it.id == product.id })
                }
                println("✅ Ürünler yüklendi → toplam: ${page.total}, gelen: ${page.products.size}, yeni skip: $skip")

                _uiState.update {
                    it.copy(
                        products = if (loadMore) it.products + updatedProducts else updatedProducts,
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

    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.OnSortClick -> {
                _uiState.value = _uiState.value.copy(showSortBottomSheet = true)
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
        }
    }

    fun dismissSortBottomSheet() {
        _uiState.value = _uiState.value.copy(showSortBottomSheet = false)
    }
}