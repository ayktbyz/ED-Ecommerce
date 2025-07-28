package com.aytbyz.enuygun.presentation.cart

import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.usecase.GetCartItemsUseCase
import com.aytbyz.enuygun.domain.usecase.RemoveFromCartUseCase
import com.aytbyz.enuygun.domain.usecase.UpdateCartItemQuantityUseCase
import com.aytbyz.enuygun.presentation.base.screen.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val updateCartItemQuantityUseCase: UpdateCartItemQuantityUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : BaseViewModel() {

    val cartItems: Flow<List<Product>> = getCartItemsUseCase()

    fun updateQuantity(product: Product, newQuantity: Int) {
        viewModelScope.launch {
            updateCartItemQuantityUseCase(product.id, newQuantity)
        }
    }

    fun removeItem(product: Product) {
        viewModelScope.launch {
            removeFromCartUseCase(product)
        }
    }
}