package com.example.ohouse.data

import com.google.gson.annotations.SerializedName

class Card(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("img_url") val imgUrl: String,
    @SerializedName("description") val desc: String,
    @SerializedName("id") val id: Int
)