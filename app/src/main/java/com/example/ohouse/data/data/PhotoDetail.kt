package com.example.ohouse.data.data

/**
 * 사진 상세 데이터 (Data Layer 계층)
 */
data class PhotoDetail(
    val card: Card,
    val user: User,
    val recommendCards: ArrayList<Card>
) {

    companion object {
        fun empty() = PhotoDetail(Card.empty(), User.empty() , ArrayList(emptyList()))
    }
}