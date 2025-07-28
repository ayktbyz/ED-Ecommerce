package com.aytbyz.enuygun.domain.model.request

data class AddToCartRequest(
    val userId: Int,
    val products: List<CartProduct>
)

data class CartProduct(
    val id: Int,
    val quantity: Int
)