package com.aytbyz.enuygun.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aytbyz.enuygun.data.local.dao.ProductDao
import com.aytbyz.enuygun.data.local.entity.CartProductEntity
import com.aytbyz.enuygun.data.local.entity.FavoriteProductEntity

@Database(
    entities = [FavoriteProductEntity::class, CartProductEntity::class],
    version = 3,
    exportSchema = false
)
abstract class EnuygunDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}