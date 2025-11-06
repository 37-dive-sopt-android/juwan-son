package com.sopt.dive.presentation.search.component

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable


@Composable
fun SearchCard(
    @DrawableRes cardFront: Int,
    @DrawableRes cardBack: Int,
    modifier: Modifier = Modifier,
) {
    var isFront by remember { mutableStateOf(true) }

    val rotation by animateFloatAsState(
        targetValue = if (isFront) 0f else 180f,
        animationSpec = tween(600),
        label = "cardRotation"
    )

    Box(
        modifier = modifier
            .size(240.dp)
            .aspectRatio(9f / 16f)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12 * density
            }
            .noRippleClickable { isFront = !isFront }
    ) {
        if (rotation <= 90f) {
            FrontView(
                cardFront = cardFront,
                modifier = Modifier
            )
        } else {
            BackView(
                cardBack = cardBack,
                modifier = Modifier.graphicsLayer {
                    rotationY = 180f
                }
            )
        }
    }
}


@Composable
private fun FrontView(
    @DrawableRes cardFront: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = painterResource(id = cardFront),
            contentDescription = "Card Front",
            modifier = Modifier
                .align(alignment = Alignment.Center),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
private fun BackView(
    @DrawableRes cardBack: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(9f / 16f)
            .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp)),
    ) {
        Image(
            painter = painterResource(id = cardBack),
            contentDescription = "Card Front",
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .size(240.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewSearchCard() {
    SearchCard(
        cardFront = R.drawable.img_search_youtube,
        cardBack = R.drawable.img_my_profile,
    )
}
