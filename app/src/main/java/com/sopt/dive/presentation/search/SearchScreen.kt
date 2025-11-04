package com.sopt.dive.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.core.component.topbar.SoptTopBar

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
) {
    SearchScreen(paddingValues)
}

@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(paddingValues)
    ) {
        SoptTopBar()
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewSearchScreen() {
    SearchScreen(
        paddingValues = PaddingValues()
    )
}