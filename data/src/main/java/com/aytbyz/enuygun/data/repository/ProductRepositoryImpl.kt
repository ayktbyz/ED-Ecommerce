package com.aytbyz.enuygun.data.repository

import com.aytbyz.enuygun.data.api.ProductApi
import com.aytbyz.enuygun.data.mapper.toDomain
import com.aytbyz.enuygun.domain.model.ProductPage
import com.aytbyz.enuygun.domain.model.response.Product
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
        sortDirection: String?,
        skip: Int,
        limit: Int
    ): Flow<ProductPage> = flow {
        try {
            val result = if (!query.isNullOrBlank()) {
                api.searchProducts(
                    query = query,
                    sortBy = sortBy,
                    order = sortDirection,
                    skip = skip,
                    limit = limit
                )
            } else {
                api.getProducts(
                    sortBy = sortBy,
                    order = sortDirection,
                    skip = skip,
                    limit = limit
                )
            }
            emit(
                ProductPage(
                    products = result.products.map { it.toDomain() },
                    total = result.total,
                    skip = result.skip,
                    limit = result.limit
                )
            )

        } catch (e: Exception) {
            emit(ProductPage(emptyList(), total = 0, skip = 0, limit = 30))
        }
    }.flowOn(dispatcher)

    override fun getProductDetail(productId: Int): Flow<Product> = flow {
        val response = api.getProductDetail(productId)
        emit(response.toDomain())
    }
}