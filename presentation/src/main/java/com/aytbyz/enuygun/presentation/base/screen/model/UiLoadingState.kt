package com.aytbyz.enuygun.presentation.base.screen.model

sealed class UiLoadingState {
    object Idle : UiLoadingState()
    object Loading : UiLoadingState()
    object Success : UiLoadingState()
    data class Error(val message: String? = null) : UiLoadingState()
}