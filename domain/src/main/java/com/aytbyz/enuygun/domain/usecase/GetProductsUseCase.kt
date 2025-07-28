package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.ProductPage
import com.aytbyz.enuygun.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        query: String? = null,
        sortBy: String? = null,
        sortDirection: String? = null,
        skip: Int = 0,
        limit: Int = 30
    ): Flow<ProductPage> {
        return repository.getProducts(query, sortBy, sortDirection, skip, limit)
    }
}