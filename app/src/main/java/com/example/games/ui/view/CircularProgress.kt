package com.example.games.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.games.ui.theme.Black
import com.example.games.ui.theme.Gray

@Composable
fun CircularProgress(isShow: Boolean) {
    if (isShow) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material.CircularProgressIndicator(
                modifier = Modifier,
                strokeWidth = ProgressIndicatorDefaults.StrokeWidth,
                color = Black
            )
        }
    }
}