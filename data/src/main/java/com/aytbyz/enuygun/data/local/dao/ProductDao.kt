package com.aytbyz.enuygun.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aytbyz.enuygun.data.local.entity.CartProductEntity
import com.aytbyz.enuygun.data.local.entity.FavoriteProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(product: FavoriteProductEntity)

    @Delete
    suspend fun deleteFavorite(product: FavoriteProductEntity)

    @Query("SELECT * FROM favorite_products")
    fun getAllFavorites(): Flow<List<FavoriteProductEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_products WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(product: CartProductEntity)

    @Delete
    suspend fun deleteCartItem(product: CartProductEntity)

    @Query("SELECT * FROM cart_products")
    fun getAllCartItems(): Flow<List<CartProductEntity>>

    @Query("UPDATE cart_products SET quantity = :quantity WHERE id = :id")
    suspend fun updateCartQuantity(id: Int, quantity: Int)
}