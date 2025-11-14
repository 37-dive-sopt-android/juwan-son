package com.sopt.dive.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.dive.presentation.community.navigation.navigateToCommunity
import com.sopt.dive.presentation.home.navigation.navigateToHome
import com.sopt.dive.presentation.mypage.navigation.navigateToMypage
import com.sopt.dive.presentation.search.navigation.navigateToSearch
import com.sopt.dive.presentation.signin.navigation.Signin
import com.sopt.dive.presentation.signin.navigation.navigateToSignin
import com.sopt.dive.presentation.signup.navigation.Signup
import com.sopt.dive.presentation.signup.navigation.navigateToSignup

class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    var isLoggedIn by mutableStateOf(false)
        private set

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tabRoute ->
            currentDestination?.hasRoute(tabRoute::class) == true
        }

    fun navigate(tabRoute: MainTab) {
        val navOptions = navOptions {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
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

    fun navigateToHome() {
        isLoggedIn = true
        navController.navigateToHome(
            navOptions {
                popUpTo<Signin> { inclusive = true }
                launchSingleTop = true
            },
        )
    }

    fun navigateToSigninFromSignup() {
        isLoggedIn = false
        navController.navigateToSignin(
            navOptions = navOptions {
                popUpTo<Signup> { inclusive = true }
            },
        )
    }

    fun navigateToSigninFromLogout() {
        isLoggedIn = false
        navController.navigateToSignin(
            navOptions = navOptions {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
                launchSingleTop = true
            },
        )
    }

    fun navigateToSignup() {
        navController.navigateToSignup()
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
