package com.example.games.view.detail

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.games.R
import com.example.games.model.GameDetail
import com.example.games.ui.theme.Black313
import com.example.games.ui.theme.Typography
import com.example.games.ui.theme.White
import com.example.games.viewmodel.detail.DetailViewModel

@Composable
fun DetailScreen(navController: NavController, id: Int) {

    val viewModel: DetailViewModel = hiltViewModel()
    val uiState: GameDetailState by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val url = remember {
        mutableStateOf(TextFieldValue())
    }

    viewModel.getGameDetail(id)

    when (uiState) {
        is GameDetailState.Success -> {
            (uiState as GameDetailState.Success).gameDetail?.let {
                Content(
                    navController = navController,
                    gameDetail = it,
                    context = context
                )
            }
        }
        is GameDetailState.Error -> {

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
private fun TopBar(navController: NavController, title: String) {
    TopAppBar(title = {
        Text(text = title)
    },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Outlined.FavoriteBorder, null)

            }
        }
    )
}

@Composable
private fun Content(navController: NavController, gameDetail: GameDetail, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        TopBar(navController, gameDetail.name ?: "")

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


    }
}