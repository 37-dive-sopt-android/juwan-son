package com.sopt.dive.presentation.home.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.home.HomeRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeNavGraph(
    padding: PaddingValues,
) {
    composable<Home> {
        HomeRoute(padding)
    }
}

@Serializable
data object Home : MainTabRoute