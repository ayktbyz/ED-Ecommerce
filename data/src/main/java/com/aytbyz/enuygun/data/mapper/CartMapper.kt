package com.aytbyz.enuygun.data.mapper

import com.aytbyz.enuygun.data.dto.AddToCartResponseDto
import com.aytbyz.enuygun.data.dto.CartProductDto
import com.aytbyz.enuygun.domain.model.response.AddToCartResponse
import com.aytbyz.enuygun.domain.model.response.CartProduct

fun AddToCartResponseDto.toDomain(): AddToCartResponse {
    return AddToCartResponse(
        id = id,
        userId = userId,
        products = products.toDomain(),
        total = total,
        discountedTotal = discountedTotal
    )
}

fun CartProductDto.toDomain(): CartProduct {
    return CartProduct(
        id = id,
        quantity = quantity
    )
}

fun List<CartProductDto>.toDomain(): List<CartProduct> {
    return map { it.toDomain() }
}