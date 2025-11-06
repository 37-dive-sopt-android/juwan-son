package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.presentation.home.model.VideoMetaInfo
import com.sopt.dive.presentation.home.model.VideoStatusChip

@Composable
fun VideoInfo(
    title: String,
    statusChip: VideoStatusChip,
    metaInfo: VideoMetaInfo,
    modifier: Modifier = Modifier,
) {
    val chipText = remember(statusChip) {
        when (statusChip) {
            VideoStatusChip.NONE -> null
            VideoStatusChip.NEW -> "NEW"
            VideoStatusChip.TRENDING -> "인기"
            VideoStatusChip.UPDATED -> "업데이트"
        }
    }

    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            chipText?.let { text ->
                Box(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Red)
                        .padding(horizontal = 4.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Text(
                text = title,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        VideoMetaInfoDisplay(metaInfo)
    }
}

@Composable
private fun VideoMetaInfoDisplay(
    metaInfo: VideoMetaInfo,
    modifier: Modifier = Modifier,
) {
    when (metaInfo) {
        is VideoMetaInfo.Basic -> {
            Text(
                text = "${metaInfo.channelName} • ${metaInfo.views} • ${metaInfo.uploadTime}",
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
            )
        }

        is VideoMetaInfo.Detailed -> {
            Column(modifier = modifier) {
                Text(
                    text = "${metaInfo.channelName} • ${metaInfo.views}",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                val additionalInfo = buildString {
                    append(metaInfo.uploadTime)
                    metaInfo.likes?.let { append(" • 좋아요 $it") }
                    metaInfo.comments?.let { append(" • 댓글 $it") }
                }
                Text(
                    text = additionalInfo,
                    color = Color.Gray,
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        is VideoMetaInfo.Minimal -> {
            Text(
                text = metaInfo.channelName,
                color = Color.Gray,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = modifier
            )
        }
    }
}