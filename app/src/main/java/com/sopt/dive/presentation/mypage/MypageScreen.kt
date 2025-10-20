package com.sopt.dive.presentation.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.component.button.SoptButton

@Preview(showBackground = true)
@Composable
private fun ReviewMypageScreen() {
    MypageScreen(
        id = "주완",
        pw = "주완주완",
        nickname = "손주완",
        onLogout = {}
    )
}

@Composable
fun MypageScreen(
    id: String,
    pw: String,
    nickname: String,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = R.string.mypage_title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.img_my_profile),
            contentDescription = stringResource(id = R.string.mypage_profile),
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "닉네임: $nickname")
        Text(text = "아이디: $id")
        Text(text = "비밀번호: $pw")


        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = stringResource(id = R.string.mypage_logout_button),
            isEnalbed = true,
            onClick = onLogout
        )
    }
}