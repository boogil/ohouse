package com.example.ohouse.data.domain

import com.example.ohouse.data.data.Card
import com.example.ohouse.data.data.PhotoDetail
import com.example.ohouse.data.data.User
import com.google.gson.annotations.SerializedName

/**
 * 사진 상세 데이터 Entity (Domain Layer 계층)
 */
data class PhotoDetailEntity(
    @SerializedName("card") val card: Card,
    @SerializedName("user") val user: User,
    @SerializedName("recommend_cards") val recommendCards: ArrayList<Card>
) : BaseEntity() {
    fun toPhotoDetail(): PhotoDetail = PhotoDetail(card, user, recommendCards)
}