package com.sopt.dive.presentation.community

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
fun CommunityRoute(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,

    ) {
    CommunityScreen(
        paddingValues = paddingValues,
        navigateUp = navigateUp,
        onClick = {},
    )
}

@Composable
fun CommunityScreen(
    paddingValues: PaddingValues,
    navigateUp: () -> Unit,
    onClick: () -> Unit,
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
private fun ReviewCommunityScreen(){
    CommunityScreen(
        paddingValues = PaddingValues(),
        navigateUp = {},
        onClick = {}
    )
}