package com.putrash.data.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("username")
    val username: String = "",
    @SerializedName("email")
    val email: String = "",
    @SerializedName("address")
    val address: Address = Address(),
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("website")
    val website: String = "",
    @SerializedName("company")
    val company: Company = Company()
): Serializable {
    data class Address(
        @SerializedName("street")
        val street: String = "",
        @SerializedName("suite")
        val suite: String = "",
        @SerializedName("city")
        val city: String = "",
        @SerializedName("zipcode")
        val zipcode: String = "",
        @SerializedName("geo")
        val geo: Geo = Geo(),
    ): Serializable {
        data class Geo(
            @SerializedName("lat")
            val lat: String = "",
            @SerializedName("lng")
            val lng: String = ""
        ): Serializable
    }

    data class Company(
        @SerializedName("name")
        val company: String = "",
        @SerializedName("catchPhrase")
        val catchPhrase: String = "",
        @SerializedName("bs")
        val bs: String = ""
    ): Serializable
}