package com.aytbyz.enuygun.data.local.repository

import com.aytbyz.enuygun.data.local.dao.ProductDao
import com.aytbyz.enuygun.data.local.entity.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalProductRepository @Inject constructor(
    private val dao: ProductDao
) {
    suspend fun addFavorite(product: FavoriteProductEntity) = dao.insertFavorite(product)
    suspend fun removeFavorite(product: FavoriteProductEntity) = dao.deleteFavorite(product)
    fun getAllFavorites(): Flow<List<FavoriteProductEntity>> = dao.getAllFavorites()
    suspend fun isFavorite(id: Int): Boolean = dao.isFavorite(id)
}