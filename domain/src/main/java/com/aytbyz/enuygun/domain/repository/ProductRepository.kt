package com.aytbyz.enuygun.domain.repository

import com.aytbyz.enuygun.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
}