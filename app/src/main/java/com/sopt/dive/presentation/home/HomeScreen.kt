package com.sopt.dive.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.core.component.topbar.SoptTopBar
import com.sopt.dive.presentation.home.component.YouTubeVideoItem
import com.sopt.dive.presentation.home.model.HomeState
import com.sopt.dive.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
    viewModel: HomeViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        paddingValues = paddingValues,
        homeState = state

    )
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    homeState: HomeState,
    modifier: Modifier = Modifier,
) {

    with(homeState) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
        ) {
            item {
                SoptTopBar()
            }

            items(
                items = videos,
                key = { item -> "video_${item.title}_${item.metaInfo}" },
                contentType = { "video_item" }
            ) { item ->
                YouTubeVideoItem(data = item)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
}