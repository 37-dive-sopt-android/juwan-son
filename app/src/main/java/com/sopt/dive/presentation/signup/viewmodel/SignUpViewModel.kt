package com.sopt.dive.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _sideEffect = MutableSharedFlow<SignUpSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun signUp(
        username: String,
        password: String,
        nickname: String,
        mbti: String
    ) {
        viewModelScope.launch {
            authRepository.postSignUp(
                username = username,
                password = password,
                name = nickname,
                email = "$username@example.com",
                age = 20
            ).onSuccess { member ->
                _sideEffect.emit(SignUpSideEffect.NavigateToSignIn)
            }.onFailure { error ->
                _sideEffect.emit(SignUpSideEffect.ShowError(error.message ?: "회원가입 실패"))
            }
        }
    }
}

sealed interface SignUpSideEffect {
    data object NavigateToSignIn : SignUpSideEffect
    data class ShowError(val message: String) : SignUpSideEffect
}