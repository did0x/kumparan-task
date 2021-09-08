package com.putrash.data.model

import java.io.Serializable

data class User(
    val id: Int = 0,
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val phone: String = "",
    val website: String = "",
    val img: String = "",
    val company: String = "",
    val address: Address = Address(),
) : Serializable {
    data class Address(
        val street: String = "",
        val suite: String = "",
        val city: String = ""
    ) : Serializable
}
