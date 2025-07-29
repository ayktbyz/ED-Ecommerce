package com.aytbyz.enuygun.presentation.components.bottomsheet.model

data class ProductFilterUIModel(
    val minPrice: Float = 0f,
    val maxPrice: Float = 50000f,
    val selectedPriceRange: ClosedFloatingPointRange<Float> = 0f..50000f,
    val minRating: Float = 0f,
    val onlyFavorites: Boolean = false
)