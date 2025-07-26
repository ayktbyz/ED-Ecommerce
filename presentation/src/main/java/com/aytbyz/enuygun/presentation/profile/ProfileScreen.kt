package com.aytbyz.enuygun.presentation.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig

@Composable
fun ProfileScreen() {
    BaseScreen(
        topBarConfig = ENTopBarConfig(
            title = stringResource(id = R.string.title_profile),
            showBackButton = false,
            onBackClick = {}
        )
    ) {

    }
}