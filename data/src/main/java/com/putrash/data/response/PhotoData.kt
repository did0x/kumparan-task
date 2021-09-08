package com.putrash.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PhotoData(
    @SerializedName("albumId")
    val albumId: Int = 0,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String = ""
) : Serializable