package com.aytbyz.enuygun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aytbyz.enuygun.ui.theme.EnuygunTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.aytbyz.enuygun.presentation.base.screen.model.UiLoadingState
import com.aytbyz.enuygun.presentation.components.loading.FullScreenLoading
import com.aytbyz.enuygun.presentation.home.HomeViewModel
import com.aytbyz.enuygun.ui.navbar.EnuygunBottomBar
import com.aytbyz.enuygun.ui.navbar.ENBottomNavItem
import com.aytbyz.enuygun.ui.navbar.ENNavigationHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Enuygun)
        super.onCreate(savedInstanceState)

        setContent {
            EnuygunTheme {
                ENApp()
            }
        }
    }
}

@Composable
fun ENApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route ?: ENBottomNavItem.Home.route

    Scaffold(
        bottomBar = {
            EnuygunBottomBar(
                selectedRoute = currentRoute,
                onItemSelected = { route ->
                    if (route != currentRoute) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ENNavigationHost(navController = navController)
        }
    }
}