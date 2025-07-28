package com.aytbyz.enuygun.presentation.product_detail

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import com.aytbyz.enuygun.domain.model.request.CartProductRequest
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.usecase.AddFavoriteUseCase
import com.aytbyz.enuygun.domain.usecase.AddToCartLocalUseCase
import com.aytbyz.enuygun.domain.usecase.AddToCartUseCase
import com.aytbyz.enuygun.domain.usecase.GetProductByIdUseCase
import com.aytbyz.enuygun.domain.usecase.IsFavoriteUseCase
import com.aytbyz.enuygun.domain.usecase.RemoveFavoriteUseCase
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.screen.BaseViewModel
import com.aytbyz.enuygun.presentation.base.screen.model.BaseUIEvent
import com.aytbyz.enuygun.presentation.product_detail.intent.ProductDetailIntent
import com.aytbyz.enuygun.presentation.product_detail.model.ProductDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToCartLocalUseCase: AddToCartLocalUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    companion object {
        private const val PRODUCT_ID = "productId"
    }

    private val _state = MutableStateFlow(ProductDetailUIState())
    val state: StateFlow<ProductDetailUIState> = _state

    init {
        showLoading(true)

        val productId = savedStateHandle.get<Int>(PRODUCT_ID)
        if (productId != null) {
            fetchProductDetail(productId)
        } else {
            showLoading(false)
        }
    }

    private fun fetchProductDetail(id: Int) {
        viewModelScope.launch {
            getProductByIdUseCase(id)
                .collect { product ->
                    val isFav = isFavoriteUseCase(product.id)
                    val updatedProduct = product.copy(isFavorite = isFav)

                    _state.update {
                        it.copy(
                            isLoading = false,
                            product = updatedProduct
                        )
                    }
                }
        }
    }

    private fun addToCart() {
        viewModelScope.launch {
            try {
                showLoading(true)

                //Service Add
                addToCartUseCase(
                    AddToCartRequest(
                        userId = 125,
                        products = listOf(
                            CartProductRequest(
                                id = state.value.product?.id ?: 0,
                                quantity = state.value.quantity
                            )
                        )
                    )
                )

                //Local DB Add
                state.value.product?.let { product ->
                    val updatedProduct = product.copy(quantity = state.value.quantity)
                    addToCartLocal(product = updatedProduct)
                }

                _state.update {
                    it.copy(
                        isLoading = false,
                        addToCartSuccess = true
                    )
                }
            } catch (e: Exception) {
                showLoading(false)
            }
        }
    }

    private fun addToCartLocal(product: Product) {
        viewModelScope.launch {
            addToCartLocalUseCase(product)
        }
    }

    fun increaseQuantity() {
        _state.update { it.copy(quantity = it.quantity + 1) }
    }

    fun decreaseQuantity() {
        _state.update { it.copy(quantity = (it.quantity - 1).coerceAtLeast(1)) }
    }

    private fun toggleFavorite() {
        viewModelScope.launch {
            val product = state.value.product ?: return@launch
            val isFav = product.isFavorite

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

            _state.update {
                it.copy(product = it.product?.copy(isFavorite = !isFav))
            }
        }
    }

    private fun showLoading(status: Boolean) {
        _state.update { it.copy(isLoading = status) }
    }

    fun onIntent(intent: ProductDetailIntent) {
        when (intent) {
            is ProductDetailIntent.IncreaseQuantity -> {
                increaseQuantity()
            }

            is ProductDetailIntent.DecreaseQuantity -> {
                decreaseQuantity()
            }

            is ProductDetailIntent.AddToCart -> {
                addToCart()
            }

            is ProductDetailIntent.ToggleFavorite -> {
                toggleFavorite()
            }
        }
    }
}