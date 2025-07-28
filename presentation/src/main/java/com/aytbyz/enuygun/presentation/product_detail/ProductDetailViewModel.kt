package com.aytbyz.enuygun.presentation.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.model.request.AddToCartRequest
import com.aytbyz.enuygun.domain.model.request.CartProduct
import com.aytbyz.enuygun.domain.usecase.AddToCartUseCase
import com.aytbyz.enuygun.domain.usecase.GetProductByIdUseCase
import com.aytbyz.enuygun.presentation.product_detail.intent.ProductDetailIntent
import com.aytbyz.enuygun.presentation.product_detail.model.ProductDetailUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

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
                .catch { throwable ->
                    showLoading(false)
                }
                .collect { product ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            product = product
                        )
                    }
                }
        }
    }

    private fun addToCart() {
        viewModelScope.launch {
            try {
                showLoading(true)

                addToCartUseCase(
                    AddToCartRequest(
                        userId = 125,
                        products = listOf(
                            CartProduct(
                                id = state.value.product?.id ?: 0,
                                quantity = state.value.quantity
                            )
                        )
                    )
                )

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

    fun increaseQuantity() {
        _state.update { it.copy(quantity = it.quantity + 1) }
    }

    fun decreaseQuantity() {
        _state.update { it.copy(quantity = (it.quantity - 1).coerceAtLeast(1)) }
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
        }
    }
}