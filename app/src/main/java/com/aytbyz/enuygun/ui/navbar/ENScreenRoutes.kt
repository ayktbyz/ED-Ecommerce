package com.aytbyz.enuygun.ui.navbar

object ENScreenRoutes {
    const val PRODUCT_ID = "productId"
    const val PRODUCT_DETAIL_WITH_ARGS = "productDetail/{$PRODUCT_ID}"

    fun productDetailRoute(id: Int): String = "productDetail/$id"
}