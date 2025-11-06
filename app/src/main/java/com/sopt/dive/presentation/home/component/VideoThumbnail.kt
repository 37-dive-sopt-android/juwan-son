package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.presentation.home.model.VideoBadge
import com.sopt.dive.presentation.home.model.VideoDuration

@Composable
fun VideoThumbnail(
    thumbnailUrl: String,
    duration: VideoDuration,
    durationText: String,
    badge: VideoBadge,
    modifier: Modifier = Modifier,
) {
    val badgeText = remember(badge) {
        when (badge) {
            VideoBadge.NONE -> null
            VideoBadge.NEW -> "NEW"
            VideoBadge.FOUR_K -> "4K"
            VideoBadge.HD -> "HD"
            VideoBadge.CC -> "CC"
        }
    }

    val durationDisplay = remember(duration, durationText) {
        when (duration) {
            VideoDuration.NONE -> null
            VideoDuration.NORMAL -> durationText
            VideoDuration.LIVE -> "LIVE"
            VideoDuration.SHORTS -> "Shorts"
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_my_profile),
            contentDescription = "Thumbnail",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        badgeText?.let { text ->
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Red)
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        durationDisplay?.let { text ->
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = text,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
