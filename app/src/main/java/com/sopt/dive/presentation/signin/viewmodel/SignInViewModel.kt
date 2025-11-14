package com.sopt.dive.presentation.signin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository,
) : ViewModel() {

    private val _sideEffect = MutableSharedFlow<SignInSideEffect>()
    val sideEffect = _sideEffect.asSharedFlow()

    fun signIn(username: String, password: String) {
        viewModelScope.launch {
            repository.postLogin(username, password)
                .onSuccess { memberId ->
                    _sideEffect.emit(SignInSideEffect.NavigateToHome(memberId))
                }
                .onFailure { throwable ->
                    _sideEffect.emit(SignInSideEffect.ShowError(throwable.message ?: "로그인 실패"))
                }
        }
    }
}

sealed interface SignInSideEffect {
    data class NavigateToHome(val memberId: Int) : SignInSideEffect
    data class ShowError(val message: String) : SignInSideEffect
}