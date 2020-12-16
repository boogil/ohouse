package com.example.ohouse.data.domain

import com.google.gson.annotations.SerializedName

/**
 * 로그인 Entity (Domain Layer 계층)
 */
data class SignInEntity(
    @SerializedName("user_id") val userId: Int
) : BaseEntity() {
    fun toId(): String = userId.toString()
}