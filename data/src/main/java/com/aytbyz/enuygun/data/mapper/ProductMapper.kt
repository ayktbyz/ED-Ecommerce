package com.aytbyz.enuygun.data.mapper

import com.aytbyz.enuygun.data.dto.ProductDto
import com.aytbyz.enuygun.data.local.entity.CartProductEntity
import com.aytbyz.enuygun.data.local.entity.FavoriteProductEntity
import com.aytbyz.enuygun.domain.model.response.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        category = category,
        thumbnail = thumbnail,
        price = price,
        images = images,
        rating = rating,
        discountPercentage = discountPercentage
    )
}

fun Product.toEntity(): CartProductEntity {
    return CartProductEntity(
        id = this.id,
        title = this.title,
        price = this.price,
        thumbnail = this.thumbnail,
        quantity = this.quantity,
        discountPercentage = this.discountPercentage
    )
}

fun CartProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        title = this.title,
        description = "",
        category = "",
        thumbnail = this.thumbnail,
        price = this.price,
        images = emptyList(),
        rating = 0f,
        quantity = quantity,
        discountPercentage = this.discountPercentage
    )
}
fun Product.toFavoriteEntity(): FavoriteProductEntity {
    return FavoriteProductEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        thumbnail = this.thumbnail,
        price = this.price,
        discountPercentage = this.discountPercentage
    )
}

fun FavoriteProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        title = this.title,
        description = "",
        category = "",
        thumbnail = this.thumbnail,
        price = this.price,
        images = emptyList(),
        rating = 0f,
        discountPercentage = this.discountPercentage
    )
}

fun Product.toCartEntity(quantity: Int = 1): CartProductEntity {
    return CartProductEntity(
        id = this.id,
        title = this.title,
        thumbnail = this.thumbnail,
        price = this.price,
        quantity = quantity,
        discountPercentage = discountPercentage
    )
}
