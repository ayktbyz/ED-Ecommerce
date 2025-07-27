package com.aytbyz.enuygun.ui.navbar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.ui.theme.Gray50
import com.aytbyz.enuygun.ui.theme.SelectedIconColor
import com.aytbyz.enuygun.ui.theme.SelectedTextColor
import com.aytbyz.enuygun.ui.theme.UnselectedIconColor
import com.aytbyz.enuygun.ui.theme.UnselectedTextColor

@Composable
fun EnuygunBottomBar(
    selectedRoute: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        ENBottomNavItem.Home,
        ENBottomNavItem.Categories,
        ENBottomNavItem.Favorite,
        ENBottomNavItem.Basket,
        ENBottomNavItem.Profile
    )

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Gray50,
                shape = RoundedCornerShape(0.dp)
            ),
        tonalElevation = 0.dp,
        containerColor = Color.White
    ) {
        items.forEach { item ->
            val isSelected = selectedRoute == item.route
            val iconTint = if (isSelected) SelectedIconColor else UnselectedIconColor
            val textColor = if (isSelected) SelectedTextColor else UnselectedTextColor
            val textStyle = if (isSelected)
                MaterialTheme.typography.titleSmall
            else
                MaterialTheme.typography.titleSmall
            val iconRes = if (isSelected) item.selectedIcon else item.icon

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = stringResource(id = item.label),
                            tint = iconTint
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = stringResource(id = item.label),
                            color = textColor,
                            style = textStyle
                        )
                    }
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}