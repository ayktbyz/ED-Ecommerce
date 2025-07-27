package com.aytbyz.enuygun.presentation.basket

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.aytbyz.enuygun.presentation.base.screen.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen() {
    BaseScreen(
        topBarConfig = ENTopBarConfig(
            title = "",
            showBackButton = false,
            onBackClick = {}
        )
    ) {

    }
}