package com.aytbyz.enuygun.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_products")
data class FavoriteProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val thumbnail: String,
    val description: String,
    val price: Double
)