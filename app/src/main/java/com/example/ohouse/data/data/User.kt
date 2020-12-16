package com.example.ohouse.data.data

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("nickname") val nickName: String,
    @SerializedName("introduction") val introduction: String,
    @SerializedName("id") val id: Int
) {
    companion object {
        fun empty() = User("","",0)
    }
}