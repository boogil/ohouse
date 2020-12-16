package com.example.ohouse.data.domain

import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.Home
import com.example.ohouse.data.data.User
import com.google.gson.annotations.SerializedName

/**
 * 홈 데이터 Entity (Domain Layer 계층)
 */
data class HomeEntity(
    @SerializedName("popular_cards") val popularCards: ArrayList<Card>,
    @SerializedName("popular_users") val popularUsers: ArrayList<User>
) : BaseEntity() {
    fun toHome(): Home = Home(popularCards, popularUsers)
}