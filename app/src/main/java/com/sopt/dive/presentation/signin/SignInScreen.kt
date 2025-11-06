package com.sopt.dive.presentation.signin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.component.button.SoptButton
import com.sopt.dive.core.component.textField.TextFieldForm
import com.sopt.dive.data.local.UserPreferences

@Preview(showBackground = true)
@Composable
private fun ReviewSignInScreen() {
    SignInScreen(
        PaddingValues(16.dp),
        navigateToHome = {},
        navigateToSignUp = {},
        userPreferences = UserPreferences(LocalContext.current),
        initialId = "",
        initialPw = "",
        modifier = Modifier
    )
}

@Composable
fun SignInRoute(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    savedId: String?,
    savedPw: String?,
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    LaunchedEffect(Unit) {
        userPreferences.getLoginInfo()?.let { userData ->
            Toast.makeText(context, "${userData.nickname}님 환영합니다!", Toast.LENGTH_SHORT).show()
            navigateToHome()
        }
    }

    SignInScreen(
        paddingValues = paddingValues,
        navigateToHome = navigateToHome,
        navigateToSignUp = navigateToSignUp,
        userPreferences = userPreferences,
        initialId = savedId ?: "",
        initialPw = savedPw ?: ""
    )
}

@Composable
fun SignInScreen(
    paddingValues: PaddingValues,
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    userPreferences: UserPreferences,
    initialId: String,
    initialPw: String,
    modifier: Modifier = Modifier,
) {

    var id by remember { mutableStateOf(initialId) }
    var pw by remember { mutableStateOf(initialPw) }

    val context = LocalContext.current
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    fun signIn() {
        val savedInfo = userPreferences.getLoginInfo()

        if (savedInfo == null) {
            Toast.makeText(context, "회원가입이 필요합니다", Toast.LENGTH_SHORT).show()
            return
        }

        if (id == savedInfo.id && pw == savedInfo.password) {
            Toast.makeText(context, "${savedInfo.nickname}님 환영합니다!", Toast.LENGTH_SHORT).show()
            navigateToHome()
        } else {
            Toast.makeText(context, "아이디 또는 비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier
            .padding(paddingValues)
            .background(color = Color.White)
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.signin_title),
            color = Color.Black,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextFieldForm(
            formText = stringResource(id = R.string.signin_id),
            value = id,
            placeholder = stringResource(id = R.string.signin_id_hint),
            onValueChange = { id = it },
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            onImeAction = { passwordFocusRequester.requestFocus() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextFieldForm(
            formText = stringResource(id = R.string.signin_pw),
            value = pw,
            placeholder = stringResource(id = R.string.signin_pw_hint),
            onValueChange = { pw = it },
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            onImeAction = {
                focusManager.clearFocus()
                if (id.isNotBlank() && pw.isNotBlank()) {
                    signIn()
                }
            },
            focusRequester = passwordFocusRequester,
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = stringResource(id = R.string.signin_login_button),
            isEnalbed = id.isNotBlank() && pw.isNotBlank(),
            onClick = { signIn() }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SoptButton(
            label = stringResource(id = R.string.signin_signup_button),
            isEnalbed = true,
            onClick = navigateToSignUp
        )
    }
}
