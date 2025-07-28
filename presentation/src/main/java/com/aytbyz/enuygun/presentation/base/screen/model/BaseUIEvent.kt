package com.aytbyz.enuygun.presentation.base.screen.model

sealed class BaseUIEvent {
    data class ShowToast(val message: String) : BaseUIEvent()
}