package com.aytbyz.enuygun.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_products")
data class CartProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val quantity: Int,
    val discountPercentage: Double,
)