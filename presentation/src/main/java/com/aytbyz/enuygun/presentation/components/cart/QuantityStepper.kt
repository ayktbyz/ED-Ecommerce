package com.aytbyz.enuygun.presentation.components.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.theme.Gray50

@Composable
fun QuantityStepper(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    minQuantity: Int = 1,
    maxQuantity: Int = 99
) {
    Row(
        modifier = modifier
            .background(Gray50, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (quantity > minQuantity) onQuantityChange(quantity - 1)
            },
            enabled = quantity > minQuantity,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_minus),
                contentDescription = "minus",
                tint = if (quantity > minQuantity) Color.Black else Color.Gray
            )
        }

        Text(
            text = quantity.toString(),
            modifier = Modifier.padding(horizontal = 8.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        IconButton(
            onClick = {
                if (quantity < maxQuantity) onQuantityChange(quantity + 1)
            },
            enabled = quantity < maxQuantity,
            modifier = Modifier.size(32.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_add),
                contentDescription = "add",
                tint = if (quantity < maxQuantity) Color.Black else Color.Gray
            )
        }
    }
}