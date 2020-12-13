package com.example.ohouse.data.entity

import com.google.gson.annotations.SerializedName

open class BaseEntity(
    val ok: Boolean = false,
    @SerializedName("error_msg") val errorMsg: String = ""
)