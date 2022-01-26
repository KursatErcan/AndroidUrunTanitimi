package com.kursat.gittigeliyor.client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val ref = "c7c2de28d81d3da4a386fc8444d574f2"
    private val baseUrl:String ="https://www.jsonbulut.com/json/"
    private var retrofit: Retrofit? = null

    fun getClient():Retrofit{
        if(retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}