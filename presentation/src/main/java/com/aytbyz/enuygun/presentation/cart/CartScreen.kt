package com.aytbyz.enuygun.presentation.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.components.cart.OrderSummary
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.Color
import com.aytbyz.enuygun.presentation.components.cart.CartItemRow

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val cartItems = viewModel.cartItems.collectAsState(initial = emptyList()).value
    val total = cartItems.sumOf { it.price * it.quantity }

    BaseScreen(
        eventFlow = viewModel.eventFlow,
        topBarConfig = ENTopBarConfig(
            title = stringResource(R.string.my_cart),
            showBackButton = false,
            onBackClick = {}
        )
    ) {
        if (cartItems.isEmpty()) {
            Text(
                text = stringResource(R.string.empty_cart_list),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            )
        } else {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(cartItems) { item ->
                        CartItemRow(
                            item = item,
                            onQuantityChange = { newQty -> viewModel.updateQuantity(item, newQty) },
                            onRemove = { viewModel.removeItem(item) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                OrderSummary(totalAmount = total)

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp),
                    enabled = cartItems.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(R.string.checkout_with_count, cartItems.size))
                }
            }
        }
    }
}