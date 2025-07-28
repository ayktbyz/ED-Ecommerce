package com.aytbyz.enuygun.ui.navbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aytbyz.enuygun.R

sealed class ENBottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    @DrawableRes val selectedIcon: Int,
    @StringRes val label: Int
) {
    object Home : ENBottomNavItem(
        "home",
        R.drawable.ic_home,
        R.drawable.ic_home_selected,
        R.string.nav_home)

    object Categories : ENBottomNavItem(
        "categories",
        R.drawable.ic_category,
        R.drawable.ic_category_selected,
        R.string.nav_categories)

    object Favorite : ENBottomNavItem(
        "favorite",
        R.drawable.ic_favorite,
        R.drawable.ic_favorite_selected,
        R.string.nav_favorite
    )

    object Basket : ENBottomNavItem(
        "basket",
        R.drawable.ic_shopping_cart,
        R.drawable.ic_shopping_cart_seleceted,
        R.string.nav_basket
    )
}