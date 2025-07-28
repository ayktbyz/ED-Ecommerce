package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.response.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        query: String? = null,
        sortBy: String? = null,
        sortDirection: String? = null
    ): Flow<List<Product>>

    fun getProductDetail(productId: Int): Flow<Product>
}