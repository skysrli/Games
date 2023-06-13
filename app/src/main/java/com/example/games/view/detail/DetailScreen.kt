package com.example.games.view.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.games.R
import com.example.games.ui.theme.Black
import com.example.games.ui.theme.Black313
import com.example.games.ui.theme.Typography
import com.example.games.ui.theme.White

@Composable
fun DetailScreen(navController: NavController) {
    val context = LocalContext.current
    val url = remember {
        mutableStateOf(TextFieldValue())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        TopBar(navController)

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

                Image(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(start = 15.dp, bottom = 15.dp)
                        .fillMaxWidth(),
                    text = "game adı",
                    color = Black,
                    style = Typography.subtitle1,
                    fontSize = 36.sp,
                    maxLines = 1
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp),
                text = "BAşlık",
                style = Typography.body2,
                fontSize = 17.sp,
                color = Black313,
                maxLines = 1
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                text = "caontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetncaontetn",
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
                            Uri.parse("https://www.google.com/")
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
                            Uri.parse("https://www.google.com/")
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

@Preview
@Composable
fun DetailScreenPreview() {
//    GamesTheme {
//        DetailScreen()
//    }
}

@Composable
private fun TopBar(navController: NavController) {
    TopAppBar(title = {
        Text(text = "sss")
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