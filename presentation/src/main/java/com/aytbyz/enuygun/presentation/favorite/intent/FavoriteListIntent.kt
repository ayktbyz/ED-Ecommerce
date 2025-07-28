package com.aytbyz.enuygun.presentation.favorite.intent

import com.aytbyz.enuygun.domain.model.response.Product

open class FavoriteListIntent {
    data class OnFavoriteToggle(val product: Product) : FavoriteListIntent()
    data class OnAddToCart(val product: Product) : FavoriteListIntent()

}