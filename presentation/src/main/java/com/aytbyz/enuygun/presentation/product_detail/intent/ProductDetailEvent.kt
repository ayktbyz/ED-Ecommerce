package com.aytbyz.enuygun.presentation.product_detail.intent

sealed class ProductDetailEvent {
    object ShowAddToCartSuccess : ProductDetailEvent()
    data class ShowError(val message: String?) : ProductDetailEvent()
}