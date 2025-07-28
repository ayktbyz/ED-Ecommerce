package com.aytbyz.enuygun.presentation.base.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.base.topbar.ENBaseTopBar
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    topBarConfig: ENTopBarConfig? = null,
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            topBarConfig?.takeIf { it.isVisible }?.let { config ->
                when {
                    config.customTopBar != null -> config.customTopBar.invoke()
                    config.title != null -> ENBaseTopBar(
                        title = config.title,
                        showBackButton = config.showBackButton,
                        onBackClick = config.onBackClick
                    )
                }
            }
        },
        modifier = modifier
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            content()
        }
    }
}