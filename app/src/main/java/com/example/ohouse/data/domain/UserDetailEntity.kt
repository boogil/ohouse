package com.example.ohouse.data.domain

import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.data.data.User
import com.google.gson.annotations.SerializedName

/**
 * 유저 상세 데이터 Entity (Domain Layer 계층)
 */
data class UserDetailEntity(
    @SerializedName("user") val user: User
) : BaseEntity() {
    fun toUser(): User = user
}