package com.sopt.dive.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.core.component.topbar.SoptTopBar
import com.sopt.dive.presentation.home.component.YouTubeVideoItem
import com.sopt.dive.presentation.home.model.ChannelProfile
import com.sopt.dive.presentation.home.model.VideoBadge
import com.sopt.dive.presentation.home.model.VideoDuration
import com.sopt.dive.presentation.home.model.VideoMetaInfo
import com.sopt.dive.presentation.home.model.VideoStatusChip
import com.sopt.dive.presentation.home.model.YouTubeVideoItemData

@Composable
fun HomeRoute(
    paddingValues: PaddingValues,
) {
    HomeScreen(paddingValues)
}

@Composable
fun HomeScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val videoItems = getDummyVideoItems()

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
            items = videoItems,
            key = { item -> "video_${item.title}_${item.metaInfo}" },
            contentType = { "video_item" }
        ) { item ->
            YouTubeVideoItem(data = item)
        }
    }
}

// 테스트용 더미입니당
private fun getDummyVideoItems(): List<YouTubeVideoItemData> {
    return listOf(
        YouTubeVideoItemData(
            thumbnailUrl = "",
            title = "안드로이드 개발 시작하기 - Kotlin 기초부터 실전까지",
            duration = VideoDuration.NORMAL,
            durationText = "25:34",
            badge = VideoBadge.NONE,
            statusChip = VideoStatusChip.NONE,
            channelProfile = ChannelProfile.SHOW,
            metaInfo = VideoMetaInfo.Basic(
                channelName = "손주완",
                views = "조회수 2.5만회",
                uploadTime = "1시간 전"
            )
        ),

        YouTubeVideoItemData(
            thumbnailUrl = "",
            title = "LIVE: SOPT 37기 실시간 방송",
            duration = VideoDuration.LIVE,
            badge = VideoBadge.NEW,
            statusChip = VideoStatusChip.TRENDING,
            channelProfile = ChannelProfile.SHOW,
            metaInfo = VideoMetaInfo.Basic(
                channelName = "SOPT",
                views = "시청자 1,234명",
                uploadTime = "LIVE"
            )
        ),

        YouTubeVideoItemData(
            thumbnailUrl = "",
            title = "Jetpack Compose 완벽 가이드 [4K 고화질]",
            duration = VideoDuration.NORMAL,
            durationText = "15:42",
            badge = VideoBadge.FOUR_K,
            statusChip = VideoStatusChip.NEW,
            channelProfile = ChannelProfile.SHOW,
            metaInfo = VideoMetaInfo.Detailed(
                channelName = "주완",
                views = "조회수 5만회",
                uploadTime = "1일 전",
                likes = "2.3천",
                comments = "456"
            )
        ),

        YouTubeVideoItemData(
            thumbnailUrl = "",
            title = "Material Design 3 디자인 시스템 완벽 분석",
            duration = VideoDuration.NORMAL,
            durationText = "18:20",
            badge = VideoBadge.CC,
            statusChip = VideoStatusChip.NONE,
            channelProfile = ChannelProfile.SHOW,
            metaInfo = VideoMetaInfo.Basic(
                channelName = "의문의 디자인 장인",
                views = "조회수 8천회",
                uploadTime = "3시간 전"
            )
        ),

        YouTubeVideoItemData(
            thumbnailUrl = "",
            title = "클린 아키텍처로 안드로이드 앱 설계하기",
            duration = VideoDuration.NORMAL,
            durationText = "32:15",
            badge = VideoBadge.HD,
            statusChip = VideoStatusChip.UPDATED,
            channelProfile = ChannelProfile.HIDE,
            metaInfo = VideoMetaInfo.Minimal(
                channelName = "손민성"
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(
        paddingValues = PaddingValues()
    )
}