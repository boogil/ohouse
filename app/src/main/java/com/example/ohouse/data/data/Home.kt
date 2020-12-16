package com.example.ohouse.data.data

/**
 * 홈 데이터 (Data Layer 계층)
 */
data class Home(
    val popularCards: ArrayList<Card>,
    val popularUsers: ArrayList<User>
) {

    companion object {
        fun empty() = Home(ArrayList(emptyList()), ArrayList(emptyList()))
    }
}