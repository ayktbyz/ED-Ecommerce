package com.aytbyz.enuygun.presentation.components.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.aytbyz.enuygun.presentation.R

@Composable
fun FavoriteIcon(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        val icon = if (isFavorite) {
            R.drawable.ic_favorite_selected
        } else {
            R.drawable.ic_favorite
        }

        val color = if (isFavorite) {
            Color.Red
        } else {
            Color.Black
        }

        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = color,
            modifier = Modifier
                .clickable { onClick() }
        )
    }
}