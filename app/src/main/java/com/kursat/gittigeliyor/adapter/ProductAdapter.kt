package com.kursat.gittigeliyor.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kursat.gittigeliyor.MyAnimationUtils
import com.kursat.gittigeliyor.databinding.ProductItemBinding
import com.kursat.gittigeliyor.model.Product

class ProductAdapter(var context: Context, val productList: List<Product>?,private val listener:ProductAdapterListener): RecyclerView.Adapter<ProductAdapter.ViewHolder>(){
    init {
        Log.d("ProductAdapter : ", productList!![0].productName!!)
    }
    class ViewHolder(val bind : ProductItemBinding) : RecyclerView.ViewHolder(bind.root){
        /*val productImage: ImageView = view.findViewById(R.id.iv_image)
        val productTitle: TextView = view.findViewById(R.id.tv_title)
        val productPrice: TextView = view.findViewById(R.id.tv_price)
        */
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = ProductItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(bind)


        //var view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        //return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = productList?.get(position)
        //holder.productTitle.text = product?.productName
        //holder.productPrice.text = product?.price
        //Glide.with(context).load(product?.images?.get(0)?.normal).centerCrop().into(holder.productImage)

        holder.bind.tvTitle.text = product?.productName
        holder.bind.tvPrice.text = product?.price
        Glide.with(context).load(product?.images?.get(0)?.normal).centerCrop().into(holder.bind.ivImage)


        holder.bind.root.setOnClickListener {
            val animation = MyAnimationUtils(context)
            holder.bind.root.startAnimation(animation.scaleUp)
            holder.bind.root.startAnimation(animation.scaleDown)
            if (product != null) {
                listener.onClickedProduct(product)
            }
            //Log.d("On Click", "${product?.productName}")
        }

    }

    override fun getItemCount(): Int = productList?.size ?: 0

}

interface ProductAdapterListener{
    fun onClickedProduct(product : Product)
}