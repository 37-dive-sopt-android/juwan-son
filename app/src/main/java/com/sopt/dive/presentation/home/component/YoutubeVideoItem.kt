package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.presentation.home.model.ChannelProfile
import com.sopt.dive.presentation.home.model.VideoBadge
import com.sopt.dive.presentation.home.model.VideoDuration
import com.sopt.dive.presentation.home.model.VideoMetaInfo
import com.sopt.dive.presentation.home.model.VideoStatusChip
import com.sopt.dive.presentation.home.model.YouTubeVideoItemData

@Composable
fun YouTubeVideoItem(
    data: YouTubeVideoItemData,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        VideoThumbnail(
            thumbnailUrl = data.thumbnailUrl,
            duration = data.duration,
            durationText = data.durationText,
            badge = data.badge
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (data.channelProfile == ChannelProfile.SHOW) {
                ChannelProfileImage(
                    profileUrl = data.channelProfileUrl
                )
            }

            VideoInfo(
                title = data.title,
                statusChip = data.statusChip,
                metaInfo = data.metaInfo,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_home_kebab),
                contentDescription = stringResource(id = R.string.home_more_icon),
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun VideoThumbnail(
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

@Composable
private fun ChannelProfileImage(
    profileUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.Gray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_my_profile),
            contentDescription = "Channel Profile",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun VideoInfo(
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

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun PreviewYouTubeVideoItem() {
    YouTubeVideoItem(
        data = YouTubeVideoItemData(
            thumbnailUrl = "",
            title = "안드로이드 Jetpack Compose 완벽 가이드 2025",
            duration = VideoDuration.NORMAL,
            durationText = "10:23",
            badge = VideoBadge.NONE,
            statusChip = VideoStatusChip.NONE,
            channelProfile = ChannelProfile.SHOW,
            metaInfo = VideoMetaInfo.Basic(
                channelName = "손주완",
                views = "조회수 1.2만회",
                uploadTime = "3시간 전"
            )
        )
    )
}
