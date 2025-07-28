package com.aytbyz.enuygun.presentation.product_detail

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.theme.Gray100
import com.aytbyz.enuygun.presentation.theme.Green50
import com.aytbyz.enuygun.presentation.theme.PrimaryBlack
import androidx.compose.ui.res.stringResource
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.components.ratingbar.RatingBarView
import com.aytbyz.enuygun.presentation.components.slider.ImageSlider
import com.aytbyz.enuygun.presentation.product_detail.intent.ProductDetailIntent

@Composable
fun ProductDetailScreen(
    productId: Int,
    onBackClick: () -> Unit,
    viewModel: ProductDetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val state = viewModel.state.collectAsState().value

    LaunchedEffect(state.addToCartSuccess) {
        if (state.addToCartSuccess) {
            onBackClick()
            Toast.makeText(context, context.getString(R.string.added_to_cart), Toast.LENGTH_SHORT)
                .show()
        }
    }

    BaseScreen(
        isLoading = state.isLoading,
        topBarConfig = ENTopBarConfig(
            isVisible = false
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Green50)
        ) {
            Box {
                ImageSlider(images = state.product?.images ?: emptyList())

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }

                    IconButton(onClick = { /* handle favorite */ }) {
                        Icon(Icons.Default.FavoriteBorder, contentDescription = null)
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = state.product?.title ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(4.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RatingBarView(
                        rating = (state.product?.rating ?: 0.0).toFloat()
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        state.product?.rating.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(Modifier.height(8.dp))

                Text(
                    text = state.product?.description ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 4
                )

                Spacer(Modifier.height(16.dp))

                Column(Modifier.fillMaxWidth()) {
                    Text(
                        "${state.product?.discountPercentage} ${stringResource(R.string.price_unit)}",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        "${state.product?.price} ${stringResource(R.string.price_unit)}",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textDecoration = TextDecoration.LineThrough,
                            color = Gray100
                        )
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    stringResource(R.string.quantity),
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(8.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(onClick = { viewModel.onIntent(ProductDetailIntent.DecreaseQuantity) }) {
                        Text("-")
                    }

                    Text(
                        state.quantity.toString(),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    OutlinedButton(onClick = { viewModel.onIntent(ProductDetailIntent.IncreaseQuantity) }) {
                        Text("+")
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        viewModel.onIntent(ProductDetailIntent.AddToCart)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlack)
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = null, tint = Color.White)

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(stringResource(R.string.add_to_cart), color = Color.White)
                }
            }
        }
    }
}