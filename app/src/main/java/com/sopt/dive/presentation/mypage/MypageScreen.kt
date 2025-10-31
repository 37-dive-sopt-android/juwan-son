package com.sopt.dive.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.component.button.SoptButton
import com.sopt.dive.core.component.topbar.SoptTopBar
import com.sopt.dive.data.local.UserPreferences

@Preview(showBackground = true)
@Composable
private fun ReviewMypageScreen() {
    MypageScreen(
        id = "주완",
        pw = "주완주완",
        nickname = "손주완",
        onLogout = {},
        paddingValues = PaddingValues(0.dp),
    )
}

@Composable
fun MypageRoute(
    paddingValues: PaddingValues,
    navigateToSignin: () -> Unit,
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }

    val userInfo = userPreferences.getLoginInfo()

    MypageScreen(
        id = userInfo?.first ?: "",
        pw = userInfo?.second ?: "",
        nickname = userInfo?.third ?: "",
        onLogout = {
            userPreferences.clearLoginInfo()
            Toast.makeText(context, "로그아웃 되었습니다", Toast.LENGTH_SHORT).show()
            navigateToSignin()
        },
        paddingValues = paddingValues,
    )
}

@Composable
fun MypageScreen(
    id: String,
    pw: String,
    nickname: String,
    onLogout: () -> Unit,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        SoptTopBar()

        Text(
            text = stringResource(id = R.string.mypage_title),
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Image(
            painter = painterResource(id = R.drawable.img_my_profile),
            contentDescription = stringResource(id = R.string.mypage_profile),
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "닉네임: $nickname", color = Color.White)

        Text(text = "아이디: $id", color = Color.White)

        Text(text = "비밀번호: $pw", color = Color.White)

        Spacer(modifier = Modifier.weight(1f))

        SoptButton(
            label = stringResource(id = R.string.mypage_logout_button),
            isEnalbed = true,
            onClick = { onLogout() }
        )
    }
}