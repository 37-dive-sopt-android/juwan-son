package com.sopt.dive.presentation.mypage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.core.component.designsystem.DiveTheme
import com.sopt.dive.data.local.UserPreferences
import com.sopt.dive.presentation.signin.SignInActivity

class MainActivity : ComponentActivity() {

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userPreferences = UserPreferences(this)

        val id = intent.getStringExtra("id")
        val pw = intent.getStringExtra("pw")
        val nickname = intent.getStringExtra("nickname")

        setContent {
            DiveTheme {
                Scaffold { innerPadding ->
                    MypageScreen(
                        modifier = Modifier.padding(innerPadding),
                        id = id.orEmpty(),
                        pw = pw.orEmpty(),
                        nickname = nickname.orEmpty(),
                        onLogout = { handleLogout() }
                    )
                }
            }
        }
    }

    private fun handleLogout() {
        userPreferences.clearLoginInfo()
        Toast.makeText(this, "로그아웃되었습니다", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, SignInActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(intent)
        finish()
    }
}