package com.aytbyz.enuygun.presentation.util

import java.text.NumberFormat
import java.util.Locale

fun formatCurrency(amount: Float): String {
    val formatter = NumberFormat.getCurrencyInstance(Locale("tr", "TR"))
    return formatter.format(amount).replace("₺", "").trim() + " TL"
}