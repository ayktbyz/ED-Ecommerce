package com.aytbyz.enuygun.data.dto

data class ProductDto(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val thumbnail: String,
    val price: Double,
    val images: List<String>,
    val rating: Float,
    val discountPercentage: Double
)