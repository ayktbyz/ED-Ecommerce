package com.aytbyz.enuygun.data.repository

import com.aytbyz.enuygun.data.api.ProductApi
import com.aytbyz.enuygun.data.mapper.toDomain
import com.aytbyz.enuygun.domain.model.Product
import com.aytbyz.enuygun.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getAllProducts(): List<Product> {
        return api.getProducts().products.map { it.toDomain() }
    }
}