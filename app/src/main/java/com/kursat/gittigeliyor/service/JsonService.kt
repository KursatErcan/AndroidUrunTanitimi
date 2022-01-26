package com.kursat.gittigeliyor.service

import com.kursat.gittigeliyor.client.ApiClient
import com.kursat.gittigeliyor.model.LoginData
import com.kursat.gittigeliyor.model.OrderData
import com.kursat.gittigeliyor.model.ProductData
import com.kursat.gittigeliyor.model.RegisterData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonService {
    // https://www.jsonbulut.com/json/userLogin.php?ref=c7c2de28d81d3da4a386fc8444d574f2&userEmail=demo@jsonbulut.com&userPass=123456&face=no

    @GET("userLogin.php")
    fun userLogin(@Query("ref") ref:String = ApiClient.ref,
                  @Query("userEmail") userEmail:String,
                  @Query("userPass") userPass:String,
                  @Query("face") face:String = "no"): Call<LoginData>

    // https://www.jsonbulut.com/json/userRegister.php?ref=c7c2de28d81d3da4a386fc8444d574f2&userName=kursat&userSurname=ercan&userPhone=05333333333&userMail=kursat@mail.com&userPass=123456
    @GET("userRegister.php")
    fun userRegister(@Query("ref") ref:String = ApiClient.ref,
                     @Query("userName") userName:String,
                     @Query("userSurname") userSurname:String,
                     @Query("userPhone") userPhone:String,
                     @Query("userMail") userMail:String,
                     @Query("userPass") userPass:String): Call<RegisterData>


    // https://www.jsonbulut.com/json/product.php?ref=c7c2de28d81d3da4a386fc8444d574f2&start=0
    @GET("product.php")
    fun product(@Query("ref") ref:String = ApiClient.ref,
                @Query("start") userEmail:String = "0",

                ): Call<ProductData>


    // https://www.jsonbulut.com/json/orderForm.php?ref=c7c2de28d81d3da4a386fc8444d574f2&customerId=1249343&productId=658&html=12

    @GET("orderForm.php")
    fun order(@Query("ref") ref:String = ApiClient.ref,
              @Query("customerId") customerId:String,
              @Query("productId") productId:String,
              @Query("html") html:String="12"

                ): Call<OrderData>
}
