package com.aytbyz.enuygun.data.repository

import com.aytbyz.enuygun.data.local.dao.ProductDao
import com.aytbyz.enuygun.data.mapper.toDomain
import com.aytbyz.enuygun.data.mapper.toEntity
import com.aytbyz.enuygun.data.mapper.toFavoriteEntity
import com.aytbyz.enuygun.domain.model.response.Product
import com.aytbyz.enuygun.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val dao: ProductDao
) : LocalRepository {
    override suspend fun addToCart(product: Product) {
        val existingItem = dao.getCartItemById(product.id)

        if (existingItem != null) {
            val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
            dao.insertCartItem(updatedItem)
        } else {
            dao.insertCartItem(product.toEntity())
        }
    }

    override fun getCartItems(): Flow<List<Product>> {
        return dao.getAllCartItems().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun removeFromCart(product: Product) {
        dao.deleteCartItem(product.toEntity())
    }

    override suspend fun updateCartItemQuantity(id: Int, quantity: Int) {
        dao.updateCartQuantity(id, quantity)
    }

    override suspend fun addFavorite(product: Product) =
        dao.insertFavorite(product.toFavoriteEntity())

    override suspend fun removeFavorite(product: Product) =
        dao.deleteFavorite(product.toFavoriteEntity())

    override fun getAllFavorites(): Flow<List<Product>> =
        dao.getAllFavorites().map { it.map { it.toDomain() } }

    override suspend fun isFavorite(id: Int): Boolean = dao.isFavorite(id)

    override suspend fun clearCart() {
        dao.clearCart()
    }
}