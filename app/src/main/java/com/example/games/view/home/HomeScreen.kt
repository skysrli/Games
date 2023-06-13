package com.example.games.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.games.R
import com.example.games.navigation.BottomSheetNavGraph
import com.example.games.navigation.Screens
import com.example.games.ui.theme.Blue
import com.example.games.ui.theme.Gray
import com.example.games.ui.theme.GrayA3A
import com.example.games.ui.theme.White

@Composable
fun HomeScreen(appNavController: NavController) {
    val navController = rememberNavController()
    var currentRoute: String? = Screens.Games.route

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray)
    ) {
        TopBar()
        Column(
            modifier = Modifier.weight(1f)
        ) {
            BottomSheetNavGraph(navController)
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            currentRoute = navBackStackEntry?.destination?.route
        }

        BottomNavigation(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            backgroundColor = White
        ) {

            val gamesSelected = currentRoute == Screens.Games.route
            BottomNavigationItem(
                selected = gamesSelected,
                onClick = {
                    navController.navigate(Screens.Games.route) {
                        popUpTo(Screens.Games.route) { inclusive = true }
                    }
                },
                icon = { Icon(Icons.Filled.Home, null) },
                label = { Text(text = stringResource(id = R.string.bottom_nav_item_games)) },
                selectedContentColor = Blue,
                unselectedContentColor = GrayA3A,
                enabled = true
            )

            val favoritesSelected = currentRoute == Screens.Favorites.route
            BottomNavigationItem(
                selected = favoritesSelected,
                onClick = {
                    navController.navigate(Screens.Favorites.route) {
                        popUpTo(Screens.Favorites.route) { inclusive = true }
                    }
                },
                icon = { Icon(Icons.Outlined.Favorite, null) },
                selectedContentColor = Blue,
                unselectedContentColor = GrayA3A,
                label = { Text(text = stringResource(id = R.string.bottom_nav_item_favorites)) },
                enabled = true
            )
        }

    }

}

@Preview
@Composable
fun HomeScreenPreview() {
//    GamesTheme() {
//        HomeScreen()
//    }
}

@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    )
}