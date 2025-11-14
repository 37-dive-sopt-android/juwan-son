package com.sopt.dive.data.service

import com.sopt.dive.core.network.BaseResponse
import com.sopt.dive.data.dto.request.LoginRequestDto
import com.sopt.dive.data.dto.request.SignUpRequestDto
import com.sopt.dive.data.dto.response.LoginResponseDto
import com.sopt.dive.data.dto.response.MemberResponseDto
import com.sopt.dive.data.dto.response.SignUpResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface AuthService {
    @POST("/api/v1/users")
    suspend fun postSignUp(
        @Body requestBody: SignUpRequestDto,
    ): BaseResponse<SignUpResponseDto>

    @POST("/api/v1/auth/login")
    suspend fun postLogin(
        @Body requestBody: LoginRequestDto,
    ): BaseResponse<LoginResponseDto>

    @GET("/api/v1/users/{id}")
    suspend fun getMemberInfo(
        @Path("id") id: Int,
    ): BaseResponse<MemberResponseDto>

}
