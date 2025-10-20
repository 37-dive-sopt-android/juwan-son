package com.sopt.dive.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.core.component.designsystem.DiveTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiveTheme {
                Scaffold { innerPadding ->
                    SignUpScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignUpSuccess = { id, pw, nickname ->
                            val resultIntent = Intent().apply {
                                putExtra("id", id)
                                putExtra("pw", pw)
                                putExtra("nickname", nickname)
                            }
                            setResult(RESULT_OK, resultIntent)
                            Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    )
                }
            }
        }
    }
}