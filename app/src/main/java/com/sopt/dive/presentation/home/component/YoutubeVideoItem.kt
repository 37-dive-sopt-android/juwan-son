package com.sopt.dive.presentation.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.core.model.ChannelProfile
import com.sopt.dive.core.model.VideoBadge
import com.sopt.dive.core.model.VideoDuration
import com.sopt.dive.core.model.VideoMetaInfo
import com.sopt.dive.core.model.VideoStatusChip
import com.sopt.dive.core.model.YouTubeVideoItemData

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
