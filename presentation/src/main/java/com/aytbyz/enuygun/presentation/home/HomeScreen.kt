package com.aytbyz.enuygun.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.aytbyz.enuygun.presentation.base.BaseScreen
import com.aytbyz.enuygun.presentation.base.topbar.ENTopBarConfig
import com.aytbyz.enuygun.presentation.components.topbar.SearchFilterSortTopBar

@Composable
fun HomeScreen() {
    val searchQuery = remember { mutableStateOf("") }

    BaseScreen(
        topBarConfig = ENTopBarConfig(
            showBackButton = false,
            customTopBar = {
                SearchFilterSortTopBar(
                    searchQuery = searchQuery.value,
                    onSearchChange = { searchQuery.value = it },
                    onFilterClick = {},
                    onSortClick = {}
                )
            },
            onBackClick = {}
        )
    ) {

    }
}