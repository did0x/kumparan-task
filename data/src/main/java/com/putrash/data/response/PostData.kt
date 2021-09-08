package com.putrash.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PostData(
    @SerializedName("userId")
    val userId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("body")
    val body: String = ""
) : Serializable