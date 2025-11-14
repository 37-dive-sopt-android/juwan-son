package com.sopt.dive

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiveApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}
