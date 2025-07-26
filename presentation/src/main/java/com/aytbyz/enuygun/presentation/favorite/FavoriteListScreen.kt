package com.aytbyz.enuygun.presentation.favorite

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.aytbyz.enuygun.presentation.R
import com.aytbyz.enuygun.presentation.base.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteListScreen() {
    BaseScreen(
        topBarConfig = ENTopBarConfig(
            title = stringResource(id = R.string.title_favorites),
            showBackButton = false,
            onBackClick = {}
        )
    ) {

    }
}