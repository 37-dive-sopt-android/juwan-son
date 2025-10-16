package com.sopt.dive.core.component.textField

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
private fun ReviewTextFieldForm() {
    TextFieldForm(
        formText = "이메일",
        value = "",
        onValueChange = {},
        placeholder = "이메일을 입력해주세요",
    )
}

@Composable
fun TextFieldForm(
    formText: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    onFocusChange: (Boolean) -> Unit = {},
    imeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    onImeAction: () -> Unit = {},
    focusRequester: FocusRequester? = null,
    isPassword: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Text(
            text = formText,
            modifier = Modifier
                .padding(horizontal = 8.dp),
            fontSize = 20.sp
        )

        SoptTextField(
            value = value,
            placeholder = placeholder,
            onValueChange = onValueChange,
            onFocusChange = onFocusChange,
            imeAction = imeAction,
            keyboardType = keyboardType,
            onImeAction = onImeAction,
            focusRequester = focusRequester,
            isPassword = isPassword,
        )
    }
}