package com.sopt.dive.presentation.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.signin.SignInRoute
import com.sopt.dive.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignup(navOptions: NavOptions? = null) {
    navigate(Signup, navOptions)
}

fun NavGraphBuilder.signupNavGraph(
    padding: PaddingValues,
    navigateToSignIn: (String, String) -> Unit,
) {
    composable<Signup> {
        SignUpRoute(
            paddingValues = padding,
            navigateToSignIn = navigateToSignIn
        )
    }
}

@Serializable
data object Signup : Route