package com.aytbyz.enuygun.domain.model

import com.aytbyz.enuygun.domain.model.response.Product

data class ProductPage(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)