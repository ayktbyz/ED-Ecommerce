package com.aytbyz.enuygun.domain.model.request

data class AddToCartRequest(
    val userId: Int,
    val products: List<CartProductRequest>
)

data class CartProductRequest(
    val id: Int,
    val quantity: Int
)