package com.kursat.gittigeliyor.model


import com.google.gson.annotations.SerializedName

data class RegisterData(
    @SerializedName("user")
    val user: List<User?>
) {
    data class User(
        @SerializedName("durum")
        val durum: Boolean,
        @SerializedName("kullaniciId")
        val kullaniciId: String?,
        @SerializedName("mesaj")
        val mesaj: String
    )
}