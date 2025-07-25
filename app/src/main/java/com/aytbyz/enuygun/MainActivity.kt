package com.aytbyz.enuygun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aytbyz.enuygun.ui.theme.EnuygunTheme
import androidx.compose.runtime.getValue
import com.aytbyz.enuygun.ui.navbar.EnuygunBottomBar
import com.aytbyz.enuygun.ui.navbar.ENBottomNavItem
import com.aytbyz.enuygun.ui.navbar.ENNavigationHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
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