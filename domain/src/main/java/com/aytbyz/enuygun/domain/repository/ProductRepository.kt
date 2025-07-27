package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProducts(
        query: String? = null,
        sortBy: String? = null,
        sortDirection: String? = null
    ): Flow<List<Product>>
}