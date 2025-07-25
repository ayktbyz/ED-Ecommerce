package com.aytbyz.enuygun.ui.navbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aytbyz.enuygun.R

sealed class ENBottomNavItem(
    val route: String,
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int
) {
    object Home : ENBottomNavItem("home", R.drawable.ic_home, R.string.nav_home)
    object Favorite : ENBottomNavItem("favorite", R.drawable.ic_favorite, R.string.nav_favorite)
    object Basket : ENBottomNavItem("basket", R.drawable.ic_shopping_cart, R.string.nav_basket)
}