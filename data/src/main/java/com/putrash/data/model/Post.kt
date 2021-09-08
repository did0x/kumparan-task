package com.putrash.data.model

import java.io.Serializable

data class Post(
    val userId: Int = 0,
    val id: Int = 0,
    val title: String = "",
    val body: String = "",
    val img: String = "",
    val name: String = "",
    val username: String = "",
    val phone: String = "",
    val website: String = "",
    val company: String = ""
) : Serializable