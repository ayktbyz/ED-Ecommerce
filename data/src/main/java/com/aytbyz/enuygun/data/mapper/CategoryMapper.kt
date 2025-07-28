package com.aytbyz.enuygun.data.mapper

import com.aytbyz.enuygun.data.dto.CategoryDto
import com.aytbyz.enuygun.domain.model.response.Category

fun CategoryDto.toDomain(): Category {
    return Category(
        name = name,
        slug = slug,
        url = url
    )
}