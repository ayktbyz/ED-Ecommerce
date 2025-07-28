package com.aytbyz.enuygun.presentation.components.ratingbar

import android.graphics.PorterDuff
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun RatingBarView(
    rating: Float,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AndroidView(
        modifier = modifier,
        factory = {
            RatingBar(context, null, android.R.attr.ratingBarStyleSmall).apply {
                numStars = 5
                stepSize = 0.5f
                this.rating = rating
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                progressDrawable.setColorFilter(
                    android.graphics.Color.BLUE,
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        },
        update = {
            if (it.rating != rating) {
                it.rating = rating
            }
        }
    )
}