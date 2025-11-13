package com.sopt.dive.presentation.home.model

import androidx.compose.runtime.Immutable
import com.sopt.dive.core.model.YouTubeVideoItemData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class HomeState(
    val videos: ImmutableList<YouTubeVideoItemData> = persistentListOf(),
)
