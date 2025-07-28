package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.ProductPage
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.model.response.ProductListResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        query: String? = null,
        sortBy: String? = null,
        sortDirection: String? = null,
        skip: Int = 0,
        limit: Int = 30
    ): Flow<ProductPage>

    fun getProductDetail(productId: Int): Flow<Product>
}