package com.sopt.dive.presentation.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Idle)
    val signInState = _signInState.asStateFlow()

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            repository.postLogin(username, password)
                .onSuccess { memberId ->
                    _signInState.value = SignInState.NavigateToHome(memberId)
                }
                .onFailure { throwable ->
                    _signInState.value = SignInState.ShowError(throwable.message ?: "로그인 실패")
                }
        }
    }

    fun resetSignInState() {
        _signInState.value = SignInState.Idle
    }
}

sealed interface SignInState {
    data object Idle : SignInState
    data class NavigateToHome(val memberId: Int) : SignInState
    data class ShowError(val message: String) : SignInState
}