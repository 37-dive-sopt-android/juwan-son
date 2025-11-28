package com.sopt.dive.presentation.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Idle)
    val signUpState = _signUpState.asStateFlow()

    fun signUp(
        username: String,
        password: String,
        nickname: String,
        mbti: String,
    ) {
        viewModelScope.launch {
            authRepository.postSignUp(
                username = username,
                password = password,
                name = nickname,
                email = "$username@example.com",
                age = 20
            ).onSuccess {
                _signUpState.value = SignUpState.NavigateToSignIn
            }.onFailure { error ->
                _signUpState.value = SignUpState.ShowError(error.message ?: "회원가입 실패")
            }
        }
    }

    fun resetSignUpState() {
        _signUpState.value = SignUpState.Idle
    }
}

sealed interface SignUpState {
    data object Idle : SignUpState
    data object NavigateToSignIn : SignUpState
    data class ShowError(val message: String) : SignUpState
}