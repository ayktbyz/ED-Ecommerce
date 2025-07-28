package com.aytbyz.enuygun.presentation.payment.intent

sealed class PaymentIntent {
    data class FullNameChanged(val value: String) : PaymentIntent()
    data class EmailChanged(val value: String) : PaymentIntent()
    data class PhoneChanged(val value: String) : PaymentIntent()
    object PayClicked : PaymentIntent()
}