package com.sopt.dive.presentation.mypage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.core.component.button.SoptButton
import com.sopt.dive.core.component.topbar.SoptTopBar
import com.sopt.dive.data.local.UserData
import com.sopt.dive.data.local.UserPreferences
import com.sopt.dive.presentation.mypage.component.MyProfileCard
import com.sopt.dive.presentation.mypage.viewmodel.MypageViewModel

@Preview(showBackground = true)
@Composable
private fun ReviewMypageScreen() {
    MypageScreen(
        userData = UserData(
            id = "주완",
            password = "주완주완",
            nickname = "손주완"
        ),
        onLogout = {},
        paddingValues = PaddingValues(0.dp),
    )
}

@Composable
fun MypageRoute(
    paddingValues: PaddingValues,
    navigateToSignin: () -> Unit,
    viewModel: MypageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val savedUser = userPreferences.getLoginInfo()

    val apiMember by viewModel.member.collectAsStateWithLifecycle()

    savedUser?.id?.let { userId ->
        val memberId = userId.toIntOrNull()
        memberId?.let {
            LaunchedEffect(it) {
                viewModel.loadMemberInfo(it)
            }
        }
    }


    MypageScreen(
        userData = savedUser ?: apiMember?.let { member ->
            UserData(
                id = member.id.toString(),
                password = member.username,
                nickname = member.name
            )
        },
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
    userData: UserData?,
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
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SoptTopBar()

        Spacer(modifier = Modifier.height(64.dp))

        MyProfileCard()

        Spacer(modifier = Modifier.height(64.dp))

        userData?.let {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "닉네임: ${it.nickname}", color = Color.White)
                Text(text = "아이디: ${it.id}", color = Color.White)
                Text(text = "비밀번호: ${it.password}", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        SoptButton(
            label = stringResource(id = R.string.mypage_logout_button),
            isEnalbed = true,
            onClick = { onLogout() }
        )
        Spacer(modifier = Modifier.height(12.dp))
    }
}