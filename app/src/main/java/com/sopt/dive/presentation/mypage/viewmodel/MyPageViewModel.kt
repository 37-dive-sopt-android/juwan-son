package com.sopt.dive.presentation.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.dive.domain.entity.Member
import com.sopt.dive.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _member = MutableStateFlow<Member?>(null)
    val member = _member.asStateFlow()

    fun loadMemberInfo(memberId: Int) {
        viewModelScope.launch {
            authRepository.getMemberInfo(memberId)
                .onSuccess { member ->
                    _member.value = member
                }
                .onFailure {
                    _member.value = null
                }
        }
    }
}