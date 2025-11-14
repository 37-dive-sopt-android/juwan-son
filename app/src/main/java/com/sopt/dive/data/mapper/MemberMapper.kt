package com.sopt.dive.data.mapper

import com.sopt.dive.data.dto.response.MemberResponseDto
import com.sopt.dive.data.dto.response.SignUpResponseDto
import com.sopt.dive.domain.entity.Member

fun SignUpResponseDto.toMember(): Member = Member(
    id = this.id,
    username = this.username,
    name = this.name,
    email = this.email,
    age = this.age,
    status = this.status
)

fun MemberResponseDto.toMember(): Member = Member(
    id = this.id,
    username = this.username,
    name = this.name,
    email = this.email,
    age = this.age,
    status = this.status
)
