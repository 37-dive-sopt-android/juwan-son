package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        "sopt_dive_prefs",
        Context.MODE_PRIVATE
    )

    fun saveLoginInfo(userData: UserData) {
        prefs.edit().apply {
            putBoolean("auto_login", true)
            putString("user_id", userData.id)
            putString("user_pw", userData.password)
            putString("user_nickname", userData.nickname)
            apply()
        }
    }

    fun getLoginInfo(): UserData? {
        if (!prefs.getBoolean("auto_login", false)) {
            return null
        }

        val id = prefs.getString("user_id", null)
        val pw = prefs.getString("user_pw", null)
        val nickname = prefs.getString("user_nickname", null)

        return if (id != null && pw != null && nickname != null) {
            UserData(id, pw, nickname)
        } else {
            null
        }
    }

    fun clearLoginInfo() {
        prefs.edit().apply {
            remove("auto_login")
            remove("user_id")
            remove("user_pw")
            remove("user_nickname")
            apply()
        }
    }
}
