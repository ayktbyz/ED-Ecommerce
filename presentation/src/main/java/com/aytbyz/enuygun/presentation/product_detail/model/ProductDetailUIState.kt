package com.aytbyz.enuygun.presentation.product_detail.model

import com.aytbyz.enuygun.domain.model.response.Product

data class ProductDetailUIState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val quantity: Int = 1,
    val addToCartSuccess: Boolean = false,
)