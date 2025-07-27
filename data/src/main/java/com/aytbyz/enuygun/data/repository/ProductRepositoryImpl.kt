package com.aytbyz.enuygun.data.repository

import com.aytbyz.enuygun.data.api.ProductApi
import com.aytbyz.enuygun.data.mapper.toDomain
import com.aytbyz.enuygun.domain.model.Product
import com.aytbyz.enuygun.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductRepository {

    override fun getProducts(
        query: String?,
        sortBy: String?,
        sortDirection: String?
    ): Flow<List<Product>> = flow {
        try {
            val result = if (!query.isNullOrBlank()) {
                api.searchProducts(
                    query = query,
                    sortBy = sortBy,
                    order = sortDirection
                )
            } else {
                api.getProducts(
                    sortBy = sortBy,
                    order = sortDirection
                )
            }
            emit(result.products.map { it.toDomain() })
        } catch (e: Exception) {
            emit(emptyList())
        }
    }.flowOn(dispatcher)
}