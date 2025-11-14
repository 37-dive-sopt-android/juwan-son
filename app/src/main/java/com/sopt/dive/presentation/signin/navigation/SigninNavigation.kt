package com.sopt.dive.presentation.signin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.signin.SignInRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignin(navOptions: NavOptions? = null) {
    navigate(Signin, navOptions)
}

fun NavGraphBuilder.signinNavGraph(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    composable<Signin> {
        SignInRoute(
            paddingValues = padding,
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp,
            viewModel = hiltViewModel()
        )
    }
}

@Serializable
data object Signin : Route