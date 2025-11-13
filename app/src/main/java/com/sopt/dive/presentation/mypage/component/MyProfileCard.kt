package com.sopt.dive.presentation.mypage.component

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateOffset
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun MyProfileCard(
    modifier: Modifier = Modifier,
) {
    var isFront by remember { mutableStateOf(true) }
    val transition = updateTransition(targetState = isFront, label = "CardTransition")

    val floatAnimationSpec = spring<Float>(stiffness = 177.8f, dampingRatio = 2f)
    val offestAnimationSpec = spring<Offset>(stiffness = 177.8f, dampingRatio = 2f)

    val rotation by transition.animateFloat(
        transitionSpec = { floatAnimationSpec },
        label = "rotation"
    ) { state ->
        if (state) 0f else 180f
    }
    val textAlpha by transition.animateFloat(
        transitionSpec = { floatAnimationSpec },
        label = "textAlpha"
    ) { state ->
        if (state) 0f else 1f
    }

    val backOffset by transition.animateOffset(
        transitionSpec = { offestAnimationSpec },
        label = "backOffset"
    ) { state ->
        if (state) Offset(0f, 0f) else Offset(24f, 24f)
    }

    Box(
        modifier = modifier
            .size(320.dp)
            .aspectRatio(9f / 16f)
            .noRippleClickable { isFront = !isFront },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .zIndex(if (isFront) 0f else 1f)
        ) {
            BackCard(textAlpha = textAlpha)
        }

        Box(
            modifier = Modifier
                .zIndex(if (isFront) 1f else 0f)
                .offset(
                    x = backOffset.x.dp,
                    y = backOffset.y.dp
                )
                .graphicsLayer {
                    rotationY = rotation
                }
        ) {
            FrontCard()
        }
    }
}


@Composable
private fun FrontCard(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(240.dp)
            .aspectRatio(9f / 16f)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 8.dp
                )
            )
            .clip(
                shape = RoundedCornerShape(
                    topStart = 8.dp,
                    topEnd = 20.dp,
                    bottomStart = 20.dp,
                    bottomEnd = 8.dp
                )
            )
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.img_my_profile),
            contentDescription = "My Profile Image",
            contentScale = ContentScale.FillBounds,
        )
    }

}

@Composable
private fun BackCard(
    textAlpha: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(240.dp)
            .aspectRatio(9f / 16f)
            .background(
                color = Color.White,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 8.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 20.dp
                )
            )
    ) {
        Text(
            text = "안두안두콩",
            color = Color.Green.copy(alpha = textAlpha),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewMyProfileCard() {
    MyProfileCard()
}
