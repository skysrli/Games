package com.example.games.view.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.games.R
import com.example.games.navigation.Screens
import com.example.games.ui.theme.*

data class AndroidVersion(
    val name: String,
    val release: String,
)

@Composable
fun GamesScreen(navController: NavController) {


    val androidNameList = listOf(

        AndroidVersion("Marshmallow", "October 5, 2015"),
        AndroidVersion("Nougat", "August 22, 2016"),
        AndroidVersion("Oreo", "August 21, 2017"),
        AndroidVersion("Pie", "August 6, 2018"),
        AndroidVersion("Android 10", "September 3, 2019"),
        AndroidVersion("Android 11", "September 8, 2020")
    )

    Column(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {

        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(androidNameList) {
                RowItem(it){
                    navController.navigate(Screens.Detail.route)
                }
            }
        }


//        Text(text = "games")
//        Button(onClick = { navController.navigate(Screens.Detail.route) }) {
//
//        }
    }

}

@Composable
fun RowItem(androidVersion: AndroidVersion,selectedItem: (AndroidVersion)-> Unit) {

    Row(
        modifier = Modifier
            .height(136.dp)
            .fillMaxWidth()
            .background(White)
            .padding(16.dp)
            .clickable { selectedItem(androidVersion) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            modifier = Modifier
                .width(120.dp)
                .height(104.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_launcher_background),
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
                    text = "The Witcher",
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
                            append("W")
                        }
                    }
                )
                Text(
                    text = "Action,shooter",
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