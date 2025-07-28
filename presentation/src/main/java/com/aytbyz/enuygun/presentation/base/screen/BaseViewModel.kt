package com.aytbyz.enuygun.presentation.base.screen

import androidx.lifecycle.ViewModel
import com.aytbyz.enuygun.presentation.base.screen.model.BaseUIEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.SharedFlow

open class BaseViewModel : ViewModel() {
    private val _eventFlow = MutableSharedFlow<BaseUIEvent>()
    val eventFlow: SharedFlow<BaseUIEvent> = _eventFlow.asSharedFlow()

    suspend fun sendUiEvent(event: BaseUIEvent) {
        _eventFlow.emit(event)
    }
}