package com.example.games.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.games.ui.theme.Gray

@Composable
fun FavoritesScreen(navController: NavController){
    Column(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {

        Text(text = "favorites")
    }
}