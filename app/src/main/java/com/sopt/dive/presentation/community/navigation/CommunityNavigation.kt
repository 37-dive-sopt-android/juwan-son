package com.sopt.dive.presentation.community.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.community.CommunityRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToCommunity(navOptions: NavOptions? = null) {
    navigate(Community, navOptions)
}

fun NavGraphBuilder.communityNavGraph(
    padding: PaddingValues,
    navigateUp : () -> Unit,
) {
    composable<Community> {
        CommunityRoute(
            padding,
            navigateUp = navigateUp,
        )
    }
}

@Serializable
data object Community : MainTabRoute