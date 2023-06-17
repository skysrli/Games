package com.example.games.view.home.games

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.games.R
import com.example.games.model.Game
import com.example.games.navigation.Screens
import com.example.games.ui.theme.*
import com.example.games.view.home.NoResultView
import com.example.games.viewmodel.home.GameListState
import com.example.games.viewmodel.home.GamesViewModel


@Composable
fun GamesScreen(navController: NavController) {

    val viewModel: GamesViewModel = hiltViewModel()
    val uiState: GameListState by viewModel.uiState.collectAsState()

    when (uiState) {

        is GameListState.Success -> {
            Content(
                gameList = (uiState as GameListState.Success).gameList,
                navController = navController,
                viewModel
            )
        }
        is GameListState.Error -> {

        }
        else -> {

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
        if (gameList.isEmpty()) {
            NoResultView(message = stringResource(id = R.string.games_screen_text_no_games))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(gameList) {
                    RowItem(it) { game ->
                        navController.navigate(
                            Screens.Detail.route.replace(
                                "{game_id}",
                                game.id.toString()
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MaterialSearchBar(viewModel: GamesViewModel) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    androidx.compose.material3.SearchBar(
        query = text,
        onQueryChange = { text = it },
        onSearch = {
            if (text.length >= 3) {
                viewModel.getFilteredGameList(text)
            } else {

            }
            active = false
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

@Composable
private fun RowItem(game: Game, selectedItem: (Game) -> Unit) {

    Row(
        modifier = Modifier
            .height(136.dp)
            .fillMaxWidth()
            .background(White)
            .padding(16.dp)
            .clickable { selectedItem(game) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .width(120.dp)
                .height(104.dp)
                .padding(end = 16.dp),
            model = game.image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {

                Text(
                    modifier = Modifier.weight(1f),
                    text = game.name ?: "",
                    color = Black,
                    style = Typography.body1,
                    fontSize = 20.sp,
                    maxLines = 2
                )

                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontStyle = Typography.body1.fontStyle,
                                color = Black,
                                fontSize = 14.sp
                            )
                        ) {
                            append(stringResource(id = R.string.gams_screen_row_item_text_metacritic))
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = Typography.subtitle2.fontStyle,
                                color = RedD8,
                                fontSize = 18.sp
                            )
                        ) {
                            game.metacritic?.let {
                                append(it.toString())
                            }
                        }
                    }
                )

                Text(
                    text = game.genres?.get(0)?.name ?: "",
                    color = Gray8A8,
                    fontSize = 13.sp,
                    style = Typography.body2,
                    maxLines = 1
                )
            }

            if(game.isFavorite){
                IconButton(
                    modifier = Modifier.size(15.dp),
                    onClick = { Log.i("icon clicked", "hey") }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete_icon),
                        contentDescription = null,
                        tint = Black,
                    )

                }
            }
        }


    }

}