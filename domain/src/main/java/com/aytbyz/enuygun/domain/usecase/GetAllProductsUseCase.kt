package com.aytbyz.enuygun.domain.usecase

import com.aytbyz.enuygun.domain.model.Product
import com.aytbyz.enuygun.domain.repository.ProductRepository

class GetAllProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(): List<Product> {
        return repository.getAllProducts()
    }
}