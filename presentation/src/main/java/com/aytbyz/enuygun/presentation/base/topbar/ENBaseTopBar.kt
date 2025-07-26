package com.aytbyz.enuygun.presentation.base.topbar

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.theme.Gray50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ENBaseTopBar(
    title: String,
    showBackButton: Boolean = false,
    onBackClick: (() -> Unit)? = null
) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = if (showBackButton && onBackClick != null) {
                {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = "back"
                        )
                    }
                }
            } else {
                {}
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black,
                navigationIconContentColor = Color.Black
            ),
            windowInsets = WindowInsets(0)
        )

        HorizontalDivider(color = Gray50, thickness = 1.dp)
    }
}