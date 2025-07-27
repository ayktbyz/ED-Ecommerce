package com.aytbyz.enuygun.presentation.components.topbar

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.components.text.CustomTextInput

@Composable
fun SearchFilterSortTopBar(
    searchQuery: String? = null,
    onSearchChange: (String) -> Unit,
    onSortClick: () -> Unit
) {
    Surface(
        color = Color.White,
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomTextInput(
                value = searchQuery ?: "",
                onValueChange = onSearchChange,
                placeholder = stringResource(R.string.placeholder_search),
                modifier = Modifier
                    .weight(1f)
                    .height(42.dp),
            )

            Spacer(modifier = Modifier.width(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onSortClick,
                    modifier = Modifier.size(28.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_sort),
                        contentDescription = "sort"
                    )
                }
            }
        }
    }
}