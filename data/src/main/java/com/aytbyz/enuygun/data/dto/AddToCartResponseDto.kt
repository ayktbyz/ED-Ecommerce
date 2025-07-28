package com.aytbyz.enuygun.data.dto

data class AddToCartResponseDto(
    val id: Int,
    val userId: Int,
    val products: List<CartProductDto>,
    val total: Double,
    val discountedTotal: Double
)