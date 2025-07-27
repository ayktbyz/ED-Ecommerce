package com.aytbyz.enuygun.data.mapper

import com.aytbyz.enuygun.data.dto.ProductDto
import com.aytbyz.enuygun.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        description = description,
        category = category,
        thumbnail = thumbnail,
        price = price,
        discountPercentage = discountPercentage
    )
}