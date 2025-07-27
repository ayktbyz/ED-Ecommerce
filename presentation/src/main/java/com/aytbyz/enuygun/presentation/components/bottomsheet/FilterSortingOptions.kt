package com.aytbyz.enuygun.presentation.components.bottomsheet

import androidx.annotation.StringRes
import com.aytbyz.enuygun.presentation.R

enum class SortDirection(val key: String) {
    ASC("asc"), DESC("desc")
}

enum class SortField(val key: String) {
    PRICE("price"), TITLE("title")
}

enum class FilterSortingOptions(
    @StringRes val titleRes: Int,
    val field: SortField,
    val direction: SortDirection
) {
    PRICE_LOW_TO_HIGH(R.string.price_low_to_high, SortField.PRICE, SortDirection.ASC),
    PRICE_HIGH_TO_LOW(R.string.price_high_to_low, SortField.PRICE, SortDirection.DESC),
    A_TO_Z(R.string.a_to_z, SortField.TITLE, SortDirection.ASC),
    Z_TO_A(R.string.z_to_a, SortField.TITLE, SortDirection.DESC)
}