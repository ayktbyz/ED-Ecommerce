package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.response.Product
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    suspend fun addToCart(product: Product)
    fun getCartItems(): Flow<List<Product>>

    suspend fun removeFromCart(product: Product)
    suspend fun updateCartItemQuantity(id: Int, quantity: Int)

    suspend fun addFavorite(product: Product)
    suspend fun removeFavorite(product: Product)
    fun getAllFavorites(): Flow<List<Product>>
    suspend fun isFavorite(id: Int): Boolean
}