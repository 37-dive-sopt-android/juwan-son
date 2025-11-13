package com.sopt.dive.presentation.search

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sopt.dive.R
import com.sopt.dive.core.component.topbar.SoptTopBar
import com.sopt.dive.presentation.search.component.SearchCard

@Composable
fun SearchRoute(
    paddingValues: PaddingValues,
) {
    SearchScreen(
        CardFront = R.drawable.img_search_youtube,
        CardBack = R.drawable.img_my_profile,
        paddingValues
    )
}

@Composable
fun SearchScreen(
    @DrawableRes CardFront: Int,
    @DrawableRes CardBack: Int,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        SoptTopBar()

        SearchCard(
            cardFront = CardFront,
            cardBack = CardBack
        )

        Text(
            text = "안녕꾸리",
            color = Color.White,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewSearchScreen() {
    SearchScreen(
        CardFront = R.drawable.img_search_youtube,
        CardBack = R.drawable.img_my_profile,
        paddingValues = PaddingValues()
    )
}