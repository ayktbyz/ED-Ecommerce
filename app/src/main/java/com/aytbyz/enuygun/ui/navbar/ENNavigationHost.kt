package com.aytbyz.enuygun.ui.navbar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.aytbyz.enuygun.presentation.basket.BasketScreen
import com.aytbyz.enuygun.presentation.categories.CategoriesScreen
import com.aytbyz.enuygun.presentation.favorite.FavoriteListScreen
import com.aytbyz.enuygun.presentation.home.HomeScreen
import com.aytbyz.enuygun.presentation.product_detail.ProductDetailScreen
import com.aytbyz.enuygun.presentation.profile.ProfileScreen

@Composable
fun ENNavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ENBottomNavItem.Home.route) {
        composable(ENBottomNavItem.Home.route) {
            HomeScreen(
                goToProductDetail = { id ->
                    navController.navigate(ENScreenRoutes.productDetailRoute(id))
                }
            )
        }
        composable(ENBottomNavItem.Categories.route) {
            CategoriesScreen()
        }
        composable(ENBottomNavItem.Favorite.route) {
            FavoriteListScreen()
        }
        composable(ENBottomNavItem.Basket.route) {
            BasketScreen()
        }
        composable(ENBottomNavItem.Profile.route) {
            ProfileScreen()
        }

        composable(
            route = ENScreenRoutes.PRODUCT_DETAIL_WITH_ARGS,
            arguments = listOf(
                navArgument(ENScreenRoutes.PRODUCT_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt(ENScreenRoutes.PRODUCT_ID)
            productId?.let {
                ProductDetailScreen(
                    productId = it,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}