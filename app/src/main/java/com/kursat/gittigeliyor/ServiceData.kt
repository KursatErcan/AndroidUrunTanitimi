package com.kursat.gittigeliyor

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.kursat.gittigeliyor.client.ApiClient
import com.kursat.gittigeliyor.model.ProductData
import com.kursat.gittigeliyor.service.JsonService
import com.kursat.gittigeliyor.view.ServiceDataListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServiceData(var context: Context, var listener: ServiceDataListener){
    val service = ApiClient.getClient().create(JsonService::class.java)


    fun getProducts(){
        val call = service.product()
        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if(response.isSuccessful) {
                    val product = response.body()
                    if(product != null){
                        var status = product.productResults[0].durum
                        if (status == true){
                            listener.onReadData(product.productResults[0].bilgiler!!)
                            //productList = product.productResults[0].bilgiler
                        }else{
                            Toast.makeText(context, "Opps! Serviste bir sorun var.\nLütfen daha sonra tekrar deneyiniz.", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        listener.onReadFailure()
                        Log.e("ProductActivity : ","Product is null")
                    }
                }else{
                    Log.e("ProductActivity : ","Response is not successfull")
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Log.d("onFailure","Failure : $t")
            }
        })
    }
}