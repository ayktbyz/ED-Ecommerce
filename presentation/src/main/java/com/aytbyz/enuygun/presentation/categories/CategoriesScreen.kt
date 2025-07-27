package com.aytbyz.enuygun.presentation.categories

import androidx.compose.runtime.Composable
import com.aytbyz.enuygun.presentation.base.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig

@Composable
fun CategoriesScreen() {
    BaseScreen(
        topBarConfig = ENTopBarConfig(
            title = "",
            showBackButton = true,
            onBackClick = {}
        )
    ) {

    }
}