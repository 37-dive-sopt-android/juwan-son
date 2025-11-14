package com.sopt.dive.data.repository

import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.mapper.toMember
import com.sopt.dive.data.service.AuthService
import com.sopt.dive.domain.entity.Member
import com.sopt.dive.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepository {

    override suspend fun postSignUp(
        username: String,
        password: String,
        name: String,
        email: String,
        age: Int,
    ): Result<Member> = runCatching {
        val response = authService.postSignUp(
            SignUpRequestDto(username, password, name, email, age)
        )
        response.data?.toMember() ?: throw IllegalStateException("회원 정보를 불러올 수 없습니다")
    }

    override suspend fun postLogin(
        username: String,
        password: String,
    ): Result<Int> = runCatching {
        val response = authService.postLogin(LoginRequestDto(username, password))
        response.data?.userId ?: throw IllegalStateException("로그인 응답 데이터가 없습니다")
    }

    override suspend fun getMemberInfo(memberId: Int): Result<Member> = runCatching {
        val response = authService.getMemberInfo(memberId)
        response.data?.toMember() ?: throw IllegalStateException("회원 정보를 불러올 수 없습니다")
    }
}

