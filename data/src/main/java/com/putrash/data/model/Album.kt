package com.putrash.data.model

import java.io.Serializable

data class Album(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val photos: Int = 0,
    val thumbnailUrl: String = "",
) : Serializable
