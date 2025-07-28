package com.aytbyz.enuygun.domain.model.response

data class AddToCartResponse(
    val id: Int,
    val userId: Int,
    val products: List<CartProduct>,
    val total: Double,
    val discountedTotal: Double
)