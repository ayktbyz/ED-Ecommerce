package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.Product
import com.aytbyz.enuygun.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    operator fun invoke(
        query: String? = null,
        sortBy: String? = null,
        sortDirection: String? = null
    ): Flow<List<Product>> {
        return repository.getProducts(query, sortBy, sortDirection)
    }
}