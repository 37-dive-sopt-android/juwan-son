package com.sopt.dive.presentation.home.model

enum class VideoDuration {
    NONE,
    NORMAL,
    LIVE,
    SHORTS
}

enum class VideoBadge {
    NONE,
    NEW,
    FOUR_K,
    HD,
    CC
}

enum class VideoStatusChip {
    NONE,
    NEW,
    TRENDING,
    UPDATED
}

enum class ChannelProfile {
    SHOW,
    HIDE
}


sealed class VideoMetaInfo {
    data class Basic(
        val channelName: String,
        val views: String,
        val uploadTime: String
    ) : VideoMetaInfo()

    data class Detailed(
        val channelName: String,
        val views: String,
        val uploadTime: String,
        val likes: String? = null,
        val comments: String? = null
    ) : VideoMetaInfo()

    data class Minimal(
        val channelName: String
    ) : VideoMetaInfo()
}

data class YouTubeVideoItemData(
    val thumbnailUrl: String,
    val title: String,
    val duration: VideoDuration = VideoDuration.NORMAL,
    val durationText: String = "",
    val badge: VideoBadge = VideoBadge.NONE,
    val statusChip: VideoStatusChip = VideoStatusChip.NONE,
    val channelProfile: ChannelProfile = ChannelProfile.SHOW,
    val channelProfileUrl: String = "",
    val metaInfo: VideoMetaInfo
)