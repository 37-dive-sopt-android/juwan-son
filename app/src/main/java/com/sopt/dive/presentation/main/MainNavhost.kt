package com.sopt.dive.presentation.main

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.sopt.dive.presentation.community.navigation.communityNavGraph
import com.sopt.dive.presentation.home.navigation.homeNavGraph
import com.sopt.dive.presentation.mypage.navigation.mypageNavGraph
import com.sopt.dive.presentation.search.navigation.searchNavGraph
import com.sopt.dive.presentation.signin.navigation.signinNavGraph
import com.sopt.dive.presentation.signup.navigation.signupNavGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    padding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigator.navController,
        startDestination = navigator.startDestination,
        modifier = modifier

    ) {
        homeNavGraph(padding)
        searchNavGraph(padding)
        communityNavGraph(
            padding,
            navigateUp = navigator::navigateUp,
        )
        mypageNavGraph(
            padding = padding,
            navigateToSignin = navigator::navigateToSigninFromLogout
        )
        signinNavGraph(
            padding = padding,
            navigateToHome = navigator::navigateToHomeFromLogin,
            navigateToSignUp = { navigator.navigateToSignup() }
        )
        signupNavGraph(
            padding = padding,
            navigateToSignIn = { id, pw ->
                navigator.navigateToSigninWithData(id, pw)
            }
        )
    }
}