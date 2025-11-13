package com.sopt.dive.core.model

enum class VideoDuration(val label: String?) {
    NONE(null),
    NORMAL(null),
    LIVE("LIVE"),
    SHORTS("Shorts");

    companion object {
        fun fromLabel(label: String?): VideoDuration =
            entries.firstOrNull { it.label == label } ?: NORMAL
    }
}

enum class VideoBadge(val label: String?) {
    NONE(null),
    NEW("NEW"),
    FOUR_K("4K"),
    HD("HD"),
    CC("CC");

    companion object {
        fun fromLabel(label: String?): VideoBadge =
            entries.firstOrNull { it.label == label } ?: NONE
    }
}

enum class VideoStatusChip(val label: String?) {
    NONE(null),
    NEW("NEW"),
    TRENDING("TRENDING"),
    UPDATED("UPDATED");

    companion object {
        fun fromLabel(label: String?): VideoStatusChip =
            entries.firstOrNull { it.label == label } ?: NONE
    }
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