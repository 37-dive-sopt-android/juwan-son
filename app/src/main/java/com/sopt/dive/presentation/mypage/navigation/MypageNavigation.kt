package com.sopt.dive.presentation.mypage.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.mypage.MypageRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMypage(navOptions: NavOptions? = null) {
    navigate(Mypage, navOptions)
}

fun NavGraphBuilder.mypageNavGraph(
    padding: PaddingValues,
    navigateToSignin: () -> Unit,
) {
    composable<Mypage> {
        MypageRoute(
            paddingValues = padding,
            navigateToSignin = navigateToSignin,
            viewModel = hiltViewModel()
        )
    }
}

@Serializable
data object Mypage : MainTabRoute