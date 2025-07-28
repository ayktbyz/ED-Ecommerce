package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Flow<Product> {
        return repository.getProductDetail(productId)
    }
}