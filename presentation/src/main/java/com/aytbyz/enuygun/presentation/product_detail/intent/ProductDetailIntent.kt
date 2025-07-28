package com.aytbyz.enuygun.presentation.product_detail.intent

sealed class ProductDetailIntent {
    object IncreaseQuantity : ProductDetailIntent()
    object DecreaseQuantity : ProductDetailIntent()

    object AddToCart : ProductDetailIntent()
    object ToggleFavorite : ProductDetailIntent()
}