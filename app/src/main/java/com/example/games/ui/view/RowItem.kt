package com.example.games.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import coil.compose.AsyncImage
import com.example.games.R
import com.example.games.data.network.model.Game
import com.example.games.ui.theme.*

@Composable
fun RowItem(
    game: Game,
    isFavorite: Boolean,
    selectedItem: (Game) -> Unit,
    deleteItem: (Game) -> Unit,
) {

    Row(
        modifier = Modifier
            .height(136.dp)
            .fillMaxWidth()
            .then(if (game.isShow) Modifier.background(Gray) else Modifier.background(White))
            .clickable { selectedItem(game) },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            modifier = Modifier
                .width(120.dp)
                .height(104.dp)
                .padding(start = 16.dp),
            model = game.image,
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp)
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
                            append(stringResource(id = R.string.games_screen_row_item_text_metacritic))
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

            if (isFavorite) {
                IconButton(
                    modifier = Modifier.size(15.dp),
                    onClick = { deleteItem(game) }) {
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
