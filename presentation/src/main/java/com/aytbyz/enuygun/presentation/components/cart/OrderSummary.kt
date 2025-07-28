package com.aytbyz.enuygun.presentation.components.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R

@Composable
fun OrderSummary(totalAmount: Double) {
    Column(modifier = Modifier.fillMaxWidth()) {

        RowSummary(
            stringResource(R.string.summary_subtotal),
            stringResource(R.string.price_with_currency, totalAmount)
        )

        RowSummary(
            stringResource(R.string.summary_shipping),
            stringResource(R.string.shipping_free)
        )

        Divider(modifier = Modifier.padding(vertical = 4.dp))

        RowSummary(
            stringResource(R.string.summary_total),
            stringResource(R.string.price_with_currency, totalAmount),
            isBold = true
        )
    }
}

@Composable
fun RowSummary(label: String, value: String, isBold: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label)
        Text(
            text = value,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.Normal
        )
    }
}