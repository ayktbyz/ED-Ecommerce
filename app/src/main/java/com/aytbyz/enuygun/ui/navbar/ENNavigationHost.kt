package com.aytbyz.enuygun.ui.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aytbyz.enuygun.presentation.basket.BasketScreen
import com.aytbyz.enuygun.presentation.favorite.FavoriteListScreen
import com.aytbyz.enuygun.presentation.home.HomeScreen

@Composable
fun ENNavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ENBottomNavItem.Home.route) {
        composable(ENBottomNavItem.Home.route) { HomeScreen() }
        composable(ENBottomNavItem.Favorite.route) { FavoriteListScreen() }
        composable(ENBottomNavItem.Basket.route) { BasketScreen() }
    }
}