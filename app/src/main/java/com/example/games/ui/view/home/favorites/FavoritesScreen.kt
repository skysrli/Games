package com.example.games.ui.view.home.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.games.R
import com.example.games.data.network.model.Game
import com.example.games.ui.navigation.Screens
import com.example.games.ui.theme.Gray
import com.example.games.ui.toastMessage
import com.example.games.ui.view.CircularProgress
import com.example.games.ui.view.RowItem
import com.example.games.ui.view.home.NoResultView

@Composable
fun FavoritesScreen(navController: NavController) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val uiState: FavoritesState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    when (uiState) {

        is FavoritesState.ApiSuccess -> {
            (uiState as FavoritesState.ApiSuccess).gameList?.let {
                Content(
                    gameList = it,
                    navController = navController,
                    viewModel
                )
            }
        }
        is FavoritesState.ApiError -> {

        }
        is FavoritesState.DeleteFavSuccess -> {
            (uiState as FavoritesState.DeleteFavSuccess).result.let {
                if (it) {
                    toastMessage(
                        context,
                        message = context.getString(R.string.favorites_screen_info_text_success_delete_fav)
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

    val lifecycleEvent = rememberLifecycleEvent()

    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            viewModel.getGameList()
        }
    }
}

@Composable
private fun Content(
    gameList: List<Game>,
    navController: NavController,
    viewModel: FavoritesViewModel,
) {
    Column(
        modifier = Modifier
            .background(Gray)
            .fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxSize()) {
            if (gameList.isEmpty()) {
                NoResultView(message = stringResource(id = R.string.favorites_screen_text_no_favorites))
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(gameList) {
                        RowItem(it, true, selectedItem = { selectedItem ->
                            //selectedItem.isShow = true
                            navController.navigate(
                                Screens.Detail.route.replace(
                                    "{game_id}",
                                    selectedItem.id.toString()
                                )
                            )
                        }, deleteItem = { deletedItem ->
                            viewModel.deleteGameFromFav(deletedItem)
                        })
                    }
                }
            }
            CircularProgress(isShow = viewModel.isLoading.value)
        }
    }
}

@Composable
fun rememberLifecycleEvent(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current): Lifecycle.Event {
    var state by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            state = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
    return state
}