package com.kursat.gittigeliyor.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.kursat.gittigeliyor.MyAnimationUtils
import com.kursat.gittigeliyor.util.UserData
import com.kursat.gittigeliyor.client.ApiClient
import com.kursat.gittigeliyor.databinding.ActivityDetailsBinding
import com.kursat.gittigeliyor.model.OrderData
import com.kursat.gittigeliyor.model.ProductDetail
import com.kursat.gittigeliyor.service.JsonService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity() {
    private lateinit var bind : ActivityDetailsBinding
    private lateinit var productDetail:ProductDetail
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.tvProductDescription.movementMethod = ScrollingMovementMethod();
        productDetail = intent.getSerializableExtra("productDetail") as ProductDetail

        bind.tvProductTitle.text = productDetail.title
        bind.tvProductDescription.text = productDetail.description
        bind.tvProductPrice.text = productDetail.price
        Glide.with(this).load(productDetail.image).centerCrop().into(bind.ivProductImage)

    }

    fun orderBtnClicked(view: View) {
        val animation = MyAnimationUtils(this)
        view.startAnimation(animation.scaleUp)
        view.startAnimation(animation.scaleDown)
        if(UserData.userId != null){
            val service = ApiClient.getClient().create(JsonService::class.java)
            val dataService = service.order(customerId= UserData.userId!!,productId= productDetail.id)

            dataService.enqueue(object : Callback<OrderData> {
                override fun onResponse(call: Call<OrderData>, response: Response<OrderData>) {
                    if (response.isSuccessful){
                        val result = response.body()
                        Toast.makeText(this@DetailsActivity, result!!.order[0].mesaj, Toast.LENGTH_SHORT).show()

                        if (result != null && result.order.get(0).durum){
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<OrderData>, t: Throwable) {
                    Log.d("Failure User Login :", t.toString())
                }
            })
        }
        else{
            Toast.makeText(this@DetailsActivity, "User Id bulunamadı!", Toast.LENGTH_SHORT).show()

        }


    }
}