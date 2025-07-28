package com.aytbyz.enuygun.domain.model.response

data class ProductListResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)