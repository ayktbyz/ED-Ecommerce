package com.aytbyz.enuygun.presentation.payment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.aytbyz.enuygun.presentation.payment.intent.PaymentIntent
import com.aytbyz.enuygun.presentation.payment.intent.PaymentUIEvent.NavigateToSuccess
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onGoPaymentSuccess: () -> Unit
) {
    val state by viewModel.paymentInfo.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.paymentFlow.collectLatest { event ->
            when (event) {
                is NavigateToSuccess -> {
                    onGoPaymentSuccess()
                }
            }
        }
    }

    BaseScreen(
        eventFlow = viewModel.eventFlow,
        topBarConfig = ENTopBarConfig(
            title = stringResource(R.string.payment),
            showBackButton = true,
            onBackClick = {
                onBackClick()
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.fullName,
                onValueChange = { viewModel.onIntent(PaymentIntent.FullNameChanged(it)) },
                label = { Text("Full Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onIntent(PaymentIntent.EmailChanged(it)) },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            OutlinedTextField(
                value = state.phone,
                onValueChange = { viewModel.onIntent(PaymentIntent.PhoneChanged(it)) },
                label = { Text("Phone") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.onIntent(PaymentIntent.PayClicked) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                enabled = state.fullName.isNotBlank()
                        && state.email.isNotBlank()
                        && state.phone.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.pay))
            }
        }
    }
}