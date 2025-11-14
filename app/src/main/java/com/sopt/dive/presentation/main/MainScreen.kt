package com.sopt.dive.presentation.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sopt.dive.presentation.main.component.MainBottomBar
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen() {
    val navigator = rememberMainNavigator()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (navigator.isLoggedIn && navigator.showBottomNavigator()) {
                MainBottomBar(
                    modifier = Modifier,
                    tabs = MainTab.entries.toPersistentList(),
                    currentTab = navigator.currentTab,
                    onTabSelected = navigator::navigate,
                )
            }
        },
    ) { padding ->
        MainNavHost(
            navigator = navigator,
            padding = padding,
            isLoggedIn = navigator.isLoggedIn,
        )
    }
}
