package com.example.ohouse.data

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("nickname") val nickName: String,
    @SerializedName("introduction") val introduction: String,
    @SerializedName("id") val id: Int
)