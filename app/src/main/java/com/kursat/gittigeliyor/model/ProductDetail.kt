package com.kursat.gittigeliyor.model

import java.io.Serializable

// TODO: data class içine product bilgiler için değişken yap direk onu at
data class ProductDetail (

    val id: String,
    val image:String,
    val title:String,
    val description:String,
    val price:String

) : Serializable
