package com.sopt.dive.presentation.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Preview(showBackground = true)
@Composable
private fun ReviewSignInScreen() {
    SignInScreen(
        onSignIn = { _, _ -> },
        onSignUpClick = {}
    )
}

@Composable
fun SignInScreen(
    onSignIn: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }

    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .padding(24.dp)
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
            imeAction = ImeAction.Done,
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
                    onSignIn(id, pw)
                }
            },
            focusRequester = passwordFocusRequester,
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = stringResource(id = R.string.signin_login_button),
            isEnalbed = id.isNotBlank() && pw.isNotBlank(),
            onClick = { onSignIn(id, pw) }
        )

        Spacer(modifier = Modifier.height(8.dp))

        SoptButton(
            label = stringResource(id = R.string.signin_signup_button),
            isEnalbed = true,
            onClick = onSignUpClick
        )
    }
}