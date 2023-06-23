package com.example.games.ui.view.home.games

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.games.R
import com.example.games.data.network.model.Game
import com.example.games.ui.navigation.Screens
import com.example.games.ui.theme.*
import com.example.games.ui.toastMessage
import com.example.games.ui.view.CircularProgress
import com.example.games.ui.view.RowItem
import com.example.games.ui.view.home.NoResultView


@Composable
fun GamesScreen(navController: NavController) {

    val viewModel: GamesViewModel = hiltViewModel()
    val uiState: GamesState by viewModel.uiState.collectAsState()

    when (uiState) {

        is GamesState.ApiSuccess -> {
            (uiState as GamesState.ApiSuccess).gameList?.let {
                Content(
                    gameList = it,
                    navController = navController,
                    viewModel
                )
            }
        }
        is GamesState.ApiError -> {

        }
    }

}

@Composable
private fun Content(gameList: List<Game>, navController: NavController, viewModel: GamesViewModel) {
    Column(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {

        MaterialSearchBar(viewModel)
        Box(modifier = Modifier.fillMaxSize()) {
            if (gameList.isEmpty()) {
                NoResultView(message = stringResource(id = R.string.games_screen_text_no_games))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(gameList) {
                        RowItem(it, false, selectedItem = { selectedGame ->
                            selectedGame.isShow = true
                            navController.navigate(
                                Screens.Detail.route.replace(
                                    "{game_id}",
                                    selectedGame.id.toString()
                                )
                            )
                        }, deleteItem = {

                        })

                    }
                }
            }
            CircularProgress(isShow = viewModel.isLoading.value)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MaterialSearchBar(viewModel: GamesViewModel) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val context = LocalContext.current

    androidx.compose.material3.SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            if (text.length > 3) {
                viewModel.getFilteredGameList(text)
                active = false
            } else {
                toastMessage(
                    context,
                    message = context.getString(R.string.games_screen_info_text_search)
                )
            }
        },
        active = active,
        onActiveChange = { active = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 24.dp),
        placeholder = {
            Text(
                text = stringResource(id = R.string.games_screen_hint_game_search),
                fontSize = 17.sp,
                style = Typography.body2,
                maxLines = 1,
                color = Gray3C,
                modifier = Modifier.fillMaxWidth()
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_search_icon),
                contentDescription = null,
                tint = Gray3C
            )
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }

                    },
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Gray3C
                )
            }
        },
        colors = SearchBarDefaults.colors(containerColor = GrayFA, dividerColor = Gray3C)

    ) {

    }
}
