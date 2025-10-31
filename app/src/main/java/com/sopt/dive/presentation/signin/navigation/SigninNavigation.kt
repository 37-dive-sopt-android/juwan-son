package com.sopt.dive.presentation.signin.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.search.SearchRoute
import com.sopt.dive.presentation.signin.SignInRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignin(
    id: String? = null,
    pw: String? = null,
    navOptions: NavOptions? = null
) {
    navigate(Signin(id = id, pw = pw), navOptions)
}

fun NavGraphBuilder.signinNavGraph(
    padding: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    composable<Signin> { backStackEntry ->
        val signin = backStackEntry.toRoute<Signin>()
        SignInRoute(
            paddingValues = padding,
            navigateToHome = navigateToHome,
            navigateToSignUp = navigateToSignUp,
            savedId = signin.id,
            savedPw = signin.pw
        )
    }
}

@Serializable
data class Signin(
    val id: String? = null,
    val pw: String? = null
) : Route