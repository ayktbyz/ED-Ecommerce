package com.aytbyz.enuygun.presentation.payment.model

import android.content.Context
import com.aytbyz.enuygun.presentation.R

data class PaymentInfo(
    val fullName: String = "",
    val email: String = "",
    val phone: String = ""
)

fun PaymentInfo.validate(context: Context): Pair<Boolean, String?> {
    return when {
        fullName.isBlank() -> false to context.getString(R.string.error_full_name_required)
        !email.contains("@") -> false to context.getString(R.string.error_invalid_email)
        phone.length < 10 -> false to context.getString(R.string.error_invalid_phone)
        else -> true to null
    }
}