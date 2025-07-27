package com.aytbyz.enuygun.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val thumbnail: String,
    val price: Double,
    val discountPercentage: Double
)