package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable

@Immutable
data class HomeState(
    val videos: List<YouTubeVideoItemData> = emptyList()
)
