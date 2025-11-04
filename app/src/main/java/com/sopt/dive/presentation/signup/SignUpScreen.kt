package com.sopt.dive.presentation.signup

import android.widget.Toast
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
private fun PreviewSignUpScreen() {
    SignUpScreen(onSignUpSuccess = { _, _, _ -> }, paddingValues = PaddingValues())
}
@Composable
fun SignUpRoute(
    paddingValues: PaddingValues,
    navigateToSignIn: (String, String) -> Unit,
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    SignUpScreen(
        paddingValues = paddingValues,
        onSignUpSuccess = { id, pw, nickname ->
            userPreferences.saveLoginInfo(id, pw, nickname)
            navigateToSignIn(id, pw)
        }
    )
}

@Composable
fun SignUpScreen(
    onSignUpSuccess: (String, String, String) -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val passwordFocusRequester = remember { FocusRequester() }
    val nicknameFocusRequester = remember { FocusRequester() }
    val mbtiFocusRequester = remember { FocusRequester() }

    val isFormValid = id.length in 6..10 &&
            pw.length in 8..12 &&
            nickname.isNotBlank() &&
            mbti.isNotBlank()

    Column(
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.signup_title),
            color = Color.Black,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextFieldForm(
            formText = stringResource(id = R.string.signup_id),
            value = id,
            onValueChange = { id = it },
            placeholder = stringResource(id = R.string.signup_id_hint),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            onImeAction = { passwordFocusRequester.requestFocus() }
        )

        TextFieldForm(
            formText = stringResource(id = R.string.signup_pw),
            value = pw,
            onValueChange = { pw = it },
            placeholder = stringResource(id = R.string.signup_pw_hint),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            onImeAction = { nicknameFocusRequester.requestFocus() },
            focusRequester = passwordFocusRequester,
            isPassword = true
        )

        TextFieldForm(
            formText = stringResource(id = R.string.signup_nickname),
            value = nickname,
            onValueChange = { nickname = it },
            placeholder = stringResource(id = R.string.signup_nickname_hint),
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            onImeAction = { mbtiFocusRequester.requestFocus() },
            focusRequester = nicknameFocusRequester
        )

        TextFieldForm(
            formText = stringResource(id = R.string.signup_mbti),
            value = mbti,
            onValueChange = { mbti = it },
            placeholder = stringResource(id = R.string.signup_mbti_hint),
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text,
            onImeAction = {
                focusManager.clearFocus()
                if (isFormValid) {
                    onSignUpSuccess(id, pw, nickname)
                }
            },
            focusRequester = mbtiFocusRequester
        )

        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = stringResource(id = R.string.signup_button),
            isEnalbed = isFormValid,
            onClick = {
                if (isFormValid) {
                    onSignUpSuccess(id, pw, nickname)
                } else {
                    Toast.makeText(context, "모든 정보를 올바르게 입력해주세요!", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}