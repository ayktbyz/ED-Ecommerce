package com.aytbyz.enuygun.presentation.components.bottomsheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.bottomsheet.BaseBottomSheetLayout

@Composable
fun ProductSortBottomSheet(
    selected: FilterSortingOptions?,
    onSelect: (FilterSortingOptions) -> Unit,
    onDismiss: () -> Unit
) {
    val options = FilterSortingOptions.entries

    BaseBottomSheetLayout(
        title = stringResource(id = R.string.sort),
        onDismissRequest = onDismiss,
    ) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelect(option) }
                    .padding(4.dp)
            ) {
                Checkbox(
                    checked = selected == option,
                    onCheckedChange = { onSelect(option) }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = stringResource(id = option.titleRes),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}