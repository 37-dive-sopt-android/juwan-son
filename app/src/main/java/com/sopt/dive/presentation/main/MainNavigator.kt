package com.sopt.dive.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.dive.presentation.community.navigation.navigateToCommunity
import com.sopt.dive.presentation.home.navigation.navigateToHome
import com.sopt.dive.presentation.mypage.navigation.navigateToMypage
import com.sopt.dive.presentation.search.navigation.navigateToSearch
import com.sopt.dive.presentation.signin.navigation.Signin
import com.sopt.dive.presentation.signin.navigation.navigateToSignin
import com.sopt.dive.presentation.signup.navigation.navigateToSignup

class MainNavigator(
    val navController: NavHostController,
) {

    val startDestination = Signin()

    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination


    val currentTab: MainTab?
        @Composable get() = MainTab.find { tabRoute ->
            currentDestination?.hasRoute(tabRoute::class) == true
        }

    fun navigate(tabRoute: MainTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tabRoute) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.SEARCH -> navController.navigateToSearch(navOptions)
            MainTab.COMMUNITY -> navController.navigateToCommunity(navOptions)
            MainTab.MYPAGE -> navController.navigateToMypage(navOptions)
        }
    }

    fun navigateUp() {
        navController.navigateUp()
    }

    fun navigateToHomeFromLogin() {
        navController.navigateToHome(
            navOptions {
                popUpTo<Signin> { inclusive = true }
                launchSingleTop = true
            }
        )
    }

    fun navigateToSigninWithData(id: String, pw: String) {
        navController.navigateToSignin(
            id = id,
            pw = pw,
            navOptions {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        )
    }

    fun navigateToCommunity(navOptions: NavOptions? = null) {
        navController.navigateToCommunity(navOptions)
    }

    fun navigateToSigninFromLogout() {
        navController.navigateToSignin(
            id = null,
            pw = null,
            navOptions {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        )
    }

    fun navigateToSignup(navOptions: NavOptions? = null) {
        navController.navigateToSignup(navOptions)
    }

    @Composable
    fun showBottomNavigator() = MainTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}