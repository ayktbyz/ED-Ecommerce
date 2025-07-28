package com.aytbyz.enuygun.domain.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val thumbnail: String,
    val price: Double,
    val images: List<String>,
    val rating: Float,
    val discountPercentage: Double
) : Parcelable