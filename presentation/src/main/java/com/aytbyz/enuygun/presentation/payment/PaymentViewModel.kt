package com.aytbyz.enuygun.presentation.payment

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.aytbyz.enuygun.domain.usecase.ClearCartUseCase
import com.aytbyz.enuygun.presentation.base.screen.BaseViewModel
import com.aytbyz.enuygun.presentation.base.screen.model.BaseUIEvent
import com.aytbyz.enuygun.presentation.payment.intent.PaymentIntent
import com.aytbyz.enuygun.presentation.payment.intent.PaymentUIEvent
import com.aytbyz.enuygun.presentation.payment.model.PaymentInfo
import com.aytbyz.enuygun.presentation.payment.model.validate
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val clearCartUseCase: ClearCartUseCase,
    @ApplicationContext private val context: Context
): BaseViewModel() {

    private val _paymentInfo = MutableStateFlow(PaymentInfo())
    val paymentInfo: StateFlow<PaymentInfo> = _paymentInfo

    private val _paymentFlow = MutableSharedFlow<PaymentUIEvent>()
    val paymentFlow = _paymentFlow.asSharedFlow()

    fun onIntent(intent: PaymentIntent) {
        when (intent) {
            is PaymentIntent.FullNameChanged -> {
                _paymentInfo.update { it.copy(fullName = intent.value) }
            }
            is PaymentIntent.EmailChanged -> {
                _paymentInfo.update { it.copy(email = intent.value) }
            }
            is PaymentIntent.PhoneChanged -> {
                _paymentInfo.update { it.copy(phone = intent.value) }
            }
            is PaymentIntent.PayClicked -> {
                viewModelScope.launch {
                    val info = _paymentInfo.value
                    val (isValid, errorMessage) = info.validate(context)

                    if (isValid) {
                        clearCartUseCase()
                        _paymentFlow.emit(PaymentUIEvent.NavigateToSuccess)
                    } else {
                        errorMessage?.let {
                            sendUiEvent(BaseUIEvent.ShowToast(it))
                        }
                    }
                }
            }
        }
    }
}