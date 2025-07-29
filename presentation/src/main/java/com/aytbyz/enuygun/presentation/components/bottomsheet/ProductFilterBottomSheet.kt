package com.aytbyz.enuygun.presentation.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.bottomsheet.BaseBottomSheetLayout
import com.aytbyz.enuygun.presentation.components.bottomsheet.model.ProductFilterUIModel
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import com.aytbyz.enuygun.presentation.util.formatCurrency
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun ProductFilterBottomSheet(
    filterState: ProductFilterUIModel,
    onApply: (ProductFilterUIModel) -> Unit,
    onClear: () -> Unit,
    onDismiss: () -> Unit
) {
    var selectedRange by remember { mutableStateOf(filterState.selectedPriceRange) }
    var minRating by remember { mutableFloatStateOf(filterState.minRating) }
    var onlyFavorites by remember { mutableStateOf(filterState.onlyFavorites) }

    BaseBottomSheetLayout(
        title = stringResource(id = R.string.filter),
        onDismissRequest = onDismiss
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(
                    R.string.price_range_label,
                    formatCurrency(selectedRange.start),
                    formatCurrency(selectedRange.endInclusive)
                )
            )

            RangeSlider(
                value = selectedRange,
                onValueChange = { selectedRange = it },
                valueRange = filterState.minPrice..filterState.maxPrice,
                steps = 10
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(
                    R.string.min_rating_label,
                    String.format("%.1f", minRating)
                )
            )

            Slider(
                value = minRating,
                onValueChange = { minRating = it },
                valueRange = 0f..5f,
                steps = 4
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = onlyFavorites,
                    onCheckedChange = { onlyFavorites = it }
                )
                Text(text = stringResource(id = R.string.only_favorites_label))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = onClear,
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = stringResource(id = R.string.clear_button))
                }

                Spacer(modifier = Modifier.width(8.dp))

                val isFilterActive = selectedRange != filterState.selectedPriceRange ||
                        minRating != filterState.minRating ||
                        onlyFavorites != filterState.onlyFavorites

                Button(
                    onClick = {
                        onApply(
                            ProductFilterUIModel(
                                selectedPriceRange = selectedRange,
                                minRating = minRating,
                                onlyFavorites = onlyFavorites
                            )
                        )
                    },
                    modifier = Modifier.weight(1f),
                    enabled = isFilterActive,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White.copy(alpha = 0.5f)
                    )
                ) {
                    Text(text = stringResource(id = R.string.apply_filter_button))
                }
            }
        }
    }
}