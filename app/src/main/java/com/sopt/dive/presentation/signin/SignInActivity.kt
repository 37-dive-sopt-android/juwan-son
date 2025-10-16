package com.sopt.dive.presentation.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.core.component.designsystem.DiveTheme
import com.sopt.dive.data.local.UserPreferences
import com.sopt.dive.presentation.mypage.MainActivity
import com.sopt.dive.presentation.signup.SignUpActivity

class SignInActivity : ComponentActivity() {

    private var registeredId: String? = null
    private var registeredPw: String? = null
    private var registeredNickname: String? = null

    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferences = UserPreferences(this)
        checkAutoLogin()
    }

    private fun checkAutoLogin() {
        val loginInfo = userPreferences.getLoginInfo()

        if (loginInfo != null) {
            val (savedId, savedPw, savedNickname) = loginInfo
            navigateToMyPage(savedId, savedPw, savedNickname)
        } else {
            showSignInScreen()
        }
    }

    private fun showSignInScreen() {
        setContent {
            DiveTheme {
                Scaffold { innerPadding ->
                    SignInScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignIn = { id, pw ->
                            if (id == registeredId && pw == registeredPw) {
                                Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                                userPreferences.saveLoginInfo(id, pw, registeredNickname.orEmpty())
                                navigateToMyPage(id, pw, registeredNickname.orEmpty())
                            } else {
                                Toast.makeText(
                                    this,
                                    "아이디 또는 비밀번호가 일치하지 않습니다",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        onSignUpClick = {
                            signUpLauncher.launch(Intent(this, SignUpActivity::class.java))
                        }
                    )
                }
            }
        }
    }

    private fun navigateToMyPage(id: String, pw: String, nickname: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("id", id)
            putExtra("pw", pw)
            putExtra("nickname", nickname)
        }
        startActivity(intent)
        finish()
    }

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let { data ->
                registeredId = data.getStringExtra("id")
                registeredPw = data.getStringExtra("pw")
                registeredNickname = data.getStringExtra("nickname")

                Toast.makeText(this, "회원가입 정보가 저장되었습니다!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}