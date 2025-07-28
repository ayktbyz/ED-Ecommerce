package com.aytbyz.enuygun.presentation.payment.intent

sealed class PaymentUIEvent {
    object NavigateToSuccess : PaymentUIEvent()
}