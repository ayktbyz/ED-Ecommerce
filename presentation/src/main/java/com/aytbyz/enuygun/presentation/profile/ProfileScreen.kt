package com.aytbyz.enuygun.presentation.profile

import androidx.compose.runtime.Composable
import com.aytbyz.enuygun.presentation.base.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig

@Composable
fun ProfileScreen() {
    BaseScreen(
        topBarConfig = ENTopBarConfig(
            title = "",
            showBackButton = false,
            onBackClick = {}
        )
    ) {

    }
}