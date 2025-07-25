package com.aytbyz.enuygun

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aytbyz.enuygun.presentation.home.HomeScreen
import com.aytbyz.enuygun.ui.theme.EnuygunTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnuygunTheme {
                HomeScreen()
            }
        }
    }
}