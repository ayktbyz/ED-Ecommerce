package com.aytbyz.enuygun.presentation.payment

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.payment.intent.PaymentUIEvent.NavigateToSuccess
import com.aytbyz.enuygun.presentation.theme.SuccessGreen
import kotlinx.coroutines.flow.collectLatest
import androidx.activity.compose.BackHandler

@Composable
fun PaymentSuccessScreen(
    viewModel: PaymentViewModel = hiltViewModel(),
    onGoHome: () -> Unit
) {
    BackHandler(enabled = true) {}

    LaunchedEffect(Unit) {
        viewModel.paymentFlow.collectLatest { event ->
            when (event) {
                is NavigateToSuccess -> {
                    onGoHome()
                }
            }
        }
    }

    BaseScreen(
        eventFlow = viewModel.eventFlow,
        topBarConfig = ENTopBarConfig(
            title = stringResource(R.string.payment),
            showBackButton = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Success Icon",
                tint = SuccessGreen,
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.payment_success_message),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onGoHome) {
                Text(stringResource(R.string.go_home))
            }
        }
    }
}
