package com.sopt.dive.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sopt.dive.core.component.designsystem.DiveTheme
import com.sopt.dive.data.local.UserPreferences

class MainActivity : ComponentActivity() {
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferences = UserPreferences(this)

        setContent {
            DiveTheme {
                MainScreen()
            }
        }
    }
}