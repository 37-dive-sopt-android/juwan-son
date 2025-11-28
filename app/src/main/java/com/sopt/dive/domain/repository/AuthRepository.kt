package com.sopt.dive.domain.repository

import com.sopt.dive.domain.entity.Member

interface AuthRepository {
    suspend fun postSignUp(
        username: String,
        password: String,
        name: String,
        email: String,
        age: Int
    ): Result<Member>

    suspend fun postLogin(
        username: String,
        password: String
    ): Result<Int>

    suspend fun getMemberInfo(
        memberId: Int
    ): Result<Member>
}
