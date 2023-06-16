package com.example.games.view.home.games

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.games.viewmodel.home.GameListState
import com.example.games.viewmodel.home.GamesViewModel


@Composable
fun GamesScreen(navController: NavController) {

    val viewModel: GamesViewModel = hiltViewModel()
    val uiState: GameListState by viewModel.uiState.collectAsState()

    when(uiState){

        is GameListState.Success -> {
            Content(gameList = (uiState as GameListState.Success).gameList, navController = navController)
        }
        is GameListState.Error -> {

        }
        else -> {

        }
    }



}

@Composable
private fun Content(gameList: List<Game>,navController: NavController){
    Column(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(gameList) {
                RowItem(it){ game ->
                    navController.navigate(Screens.Detail.route.replace("{game_id}",game.id.toString()))
                }
            }
        }
    }
}

@Composable
private fun RowItem(game: Game,selectedItem: (Game)-> Unit) {

    Row(
        modifier = Modifier
            .height(136.dp)
            .fillMaxWidth()
            .background(White)
            .padding(16.dp)
            .clickable { selectedItem(game) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(modifier = Modifier
            .width(120.dp)
            .height(104.dp)
            .padding(end = 16.dp),
            model = game.image,
            contentScale = ContentScale.Crop,
            contentDescription = null)

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
                    text = game.name,
                    color = Black,
                    style = Typography.body1,
                    fontSize = 20.sp,
                    maxLines = 2
                )

                Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontStyle = Typography.body1.fontStyle, color = Black, fontSize = 14.sp)) {
                            append(stringResource(id = R.string.gams_screen_row_item_text_metacritic))
                        }
                        withStyle(
                            style = SpanStyle(
                                fontStyle = Typography.subtitle2.fontStyle,
                                color = RedD8,
                                fontSize = 18.sp
                            )
                        ) {
                            append(game.metacritic.toString())
                        }
                    }
                )
                Text(
                    text = game.genres[0].name,
                    color = Gray8A8,
                    fontSize = 13.sp,
                    style = Typography.body2,
                    maxLines = 1
                )

            }

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