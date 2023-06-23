package com.example.games.ui.view.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.games.R
import com.example.games.data.network.model.GameDetail
import com.example.games.ui.theme.Black313
import com.example.games.ui.theme.Typography
import com.example.games.ui.theme.White
import com.example.games.ui.toastMessage
import com.example.games.ui.view.CircularProgress
import com.example.games.ui.view.home.games.GamesState
import com.example.games.ui.view.home.games.GamesViewModel


@Composable
fun DetailScreen(navController: NavController, id: Int) {

    val viewModel: DetailViewModel = hiltViewModel()
    val uiState: DetailState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    viewModel.getGameDetail(id)

    when (uiState) {
        is DetailState.ApiSuccess -> {
            (uiState as DetailState.ApiSuccess).gameDetail?.let { it ->
                Content(
                    navController = navController,
                    gameDetail = it,
                    context = context,
                    viewModel
                )
            }
        }
        is DetailState.ApiError -> {

        }

        is DetailState.AddFavSuccess -> {
            (uiState as DetailState.AddFavSuccess).result.let {
                if (it) {
                    toastMessage(
                        context,
                        message = context.getString(R.string.detail_screen_info_text_success_add_fav)
                    )

                } else {
                    toastMessage(
                        context,
                        message = context.getString(R.string.unexpected_error_message)
                    )

                }
            }
        }
    }


}

@Preview
@Composable
fun DetailScreenPreview() {
//    GamesTheme {
//        DetailScreen()
//    }
}

@Composable
private fun TopBar(
    navController: NavController,
    title: String,
    viewModel: DetailViewModel,
    gameDetail: GameDetail,
) {
    val gamesViewModel: GamesViewModel = hiltViewModel()
    val uiState: GamesState by gamesViewModel.uiState.collectAsState()

    TopAppBar(title = {
        Text(text = title)
    },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actions = {
            IconButton(onClick = {
                val game =
                    (uiState as GamesState.ApiSuccess).gameList?.find { it.id == gameDetail.id }
                game?.let { viewModel.addFav(it) }
            }) {
                Icon(Icons.Outlined.FavoriteBorder, null)
            }
        }
    )
}

@Composable
private fun Content(
    navController: NavController,
    gameDetail: GameDetail,
    context: Context,
    viewModel: DetailViewModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        TopBar(navController, gameDetail.name ?: "", viewModel, gameDetail)
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(275.dp)
                ) {

                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = gameDetail.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(start = 15.dp, bottom = 15.dp)
                            .fillMaxWidth(),
                        text = gameDetail.name ?: "",
                        color = White,
                        style = Typography.subtitle1,
                        fontSize = 36.sp,
                        maxLines = 1
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                    text = stringResource(id = R.string.detail_screen_text_game_description),
                    style = Typography.body2,
                    fontSize = 17.sp,
                    color = Black313,
                    maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    text = gameDetail.description ?: "",
                    style = Typography.body2,
                    fontSize = 10.sp,
                    color = Black313,
                    maxLines = 4
                )
                Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            val urlIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(gameDetail.redditUrl)
                            )
                            context.startActivity(urlIntent)
                        },
                    text = stringResource(id = R.string.detail_screen_text_visit_reddit),
                    color = Black313,
                    style = Typography.body2,
                    fontSize = 17.sp
                )
                Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clickable {
                            val urlIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(gameDetail.website)
                            )
                            context.startActivity(urlIntent)

                        },
                    text = stringResource(id = R.string.detail_screen_text_visit_website),
                    color = Black313,
                    style = Typography.body2,
                    fontSize = 17.sp
                )
                Divider(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
            }
            CircularProgress(isShow = viewModel.isLoading.value)
        }
    }
}