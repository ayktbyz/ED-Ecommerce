package com.aytbyz.enuygun.presentation.base.topbar

import androidx.compose.runtime.Composable

data class ENTopBarConfig(
    val title: String? = null,
    val showBackButton: Boolean = false,
    val onBackClick: (() -> Unit)? = null,
    val customTopBar: (@Composable (() -> Unit))? = null,
    val isVisible: Boolean = true
)