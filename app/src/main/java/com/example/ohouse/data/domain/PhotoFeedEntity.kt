package com.example.ohouse.data.domain

import com.example.ohouse.data.data.Card
import com.google.gson.annotations.SerializedName

/**
 * 사진피드 Entity (Domain Layer 계층)
 */
data class PhotoFeedEntity(
    @SerializedName("cards") val cards: ArrayList<Card>
) : BaseEntity() {
    fun toCards(): ArrayList<Card> = cards

}