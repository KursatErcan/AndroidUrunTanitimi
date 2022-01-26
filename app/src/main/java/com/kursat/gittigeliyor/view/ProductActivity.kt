package com.kursat.gittigeliyor.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.kursat.gittigeliyor.GridSpacingItemDecoration
import com.kursat.gittigeliyor.ServiceData
import com.kursat.gittigeliyor.adapter.ProductAdapter
import com.kursat.gittigeliyor.adapter.ProductAdapterListener
import com.kursat.gittigeliyor.databinding.ActivityProductBinding
import com.kursat.gittigeliyor.model.Product
import com.kursat.gittigeliyor.model.ProductDetail


class ProductActivity : AppCompatActivity(), ProductAdapterListener,ServiceDataListener {
    private lateinit var bind: ActivityProductBinding
    private var productList: List<Product>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityProductBinding.inflate(layoutInflater)
        setContentView(bind.root)
        bind.ivNoProductFound.visibility = View.INVISIBLE
        ServiceData(this,this).getProducts()

        Log.d("ProductList Size : 3", "${productList?.size}")

    }

    override fun onClickedProduct(product: Product) {
        var productDetail = ProductDetail(
            product.productId!!,
            product.images?.get(0)?.normal!!,
            product.productName!!,
            product.description!!,
            product.price!!
        )
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("productDetail",productDetail)
        startActivity(intent)
    }

    override fun onReadData(productList: List<Product>) {
        var adapter = ProductAdapter(this, productList, this)
        bind.recyclerView.layoutManager = GridLayoutManager(this, 2)
        bind.recyclerView.adapter = adapter

        bind.recyclerView.addItemDecoration(GridSpacingItemDecoration(12))
    }

    override fun onReadFailure() {
        bind.ivNoProductFound.visibility = View.VISIBLE
    }


}
interface ServiceDataListener {
    fun onReadData(productList: List<Product>)
    fun onReadFailure()
}








