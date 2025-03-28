package com.cpixelstudios.newsnow.Adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cpixelstudios.newsnow.Activitys.ProductsDetailsActivity
import com.cpixelstudios.newsnow.Adapters.Cart.CartItems
import com.cpixelstudios.newsnow.Api.RetrofitClient
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.R

class CartAdapter(
    val context: Context,
    private val items: List<CartItems>,
    var click: ProductItemClick?,
    var quantityChangeListener: CartItemQuantityChangeListener?
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducts: ImageView = itemView.findViewById(R.id.imgProducts)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtOfferPrice: TextView = itemView.findViewById(R.id.txtOfferPrice)
        val txtTotalPrice: TextView = itemView.findViewById(R.id.txtTotalPrice)
        val txtQuantity: TextView = itemView.findViewById(R.id.txtQuantity)
        val cartQty: TextView = itemView.findViewById(R.id.cartQty)
        val linearDecrement: LinearLayout = itemView.findViewById(R.id.linearDecrement)
        val linearIncrement: LinearLayout = itemView.findViewById(R.id.linearIncrement)
        val imgDelete: ImageView = itemView.findViewById(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_items_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        Glide.with(holder.imgProducts)
            .load(RetrofitClient.Image_URL2+item.product_image)
            .error(R.drawable.logo)
            .into(holder.imgProducts)
        holder.txtTitle.text = item.product_title
        holder.txtQuantity.text = item.quantity
        holder.txtOfferPrice.text =  "(Price : ₹"+item.offer_price+" )"
        holder.cartQty.text = item.cart_quantity
        val cartQ = intArrayOf(holder.cartQty.text.toString().toInt())


        val finalAmount: Int = item.offer_price.toInt() * item.cart_quantity.toInt()
        holder.txtTotalPrice.text = "Total Price : ₹ "+finalAmount

        holder.imgDelete.setOnClickListener {
            val animations = ViewController.animation()
            holder.imgDelete.startAnimation(animations)
            quantityChangeListener?.onDeleteCartItem(item)
        }

        holder.linearDecrement.setOnClickListener {
            val animations = ViewController.animation()
            holder.linearDecrement.startAnimation(animations)
            if (cartQ[0] > 1) {
                cartQ[0]--
                holder.cartQty.text = "" + cartQ[0]
                holder.cartQty.text = cartQ[0].toString()
                val carstQty = holder.cartQty.text.toString()
                if(cartQ[0]==1){
                    click!!.onAddToCartClicked(item, carstQty,1)
                }else{
                    click!!.onAddToCartClicked(item, carstQty,1)
                }
                // holder.binding.addToCartBtn.performClick()
            }
        }

        holder.linearIncrement.setOnClickListener {
            val animations = ViewController.animation()
            holder.linearIncrement.startAnimation(animations)

            val cartQty = holder.cartQty.text.toString()
            if (item.max_order_quantity.toInt()<=cartQty.toInt()){
                ViewController.showToast(context,"Max Quantity only for "+item.max_order_quantity)
                return@setOnClickListener
            }
            if (item.stock == cartQty) {
                ViewController.showToast(context,"Stock Limit only " + item.stock)
            }else{
                cartQ[0]++
                if (item.max_order_quantity.toInt()<=cartQty.toInt()){
                    Toast.makeText(context, "Can't add Max Quantity for this Product", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                holder.cartQty.text = cartQ[0].toString()
                val cartQty1 = holder.cartQty.text.toString()
                holder.cartQty.text = "" + cartQ.get(0)


                if (!ViewController.noInterNetConnectivity(context)) {
                    ViewController.showToast(context, "Please check your connection ")
                } else {
                    if (cartQty1 == "1")
                        click!!.onAddToCartClicked(item, cartQty1,1)
                    else{
                        click!!.onAddToCartClicked(item, cartQty1,1)
                    }
                }

                // holder.binding.addToCartBtn.performClick()

            }
        }

        holder.itemView.setOnClickListener {
            val animations = ViewController.animation()
            holder.imgProducts.startAnimation(animations)
            val intent = Intent(context, ProductsDetailsActivity::class.java).apply {
                putExtra("productsId", item.products_id)
            }
            context.startActivity(intent)
            if (context is Activity) {
                context.overridePendingTransition(R.anim.from_right, R.anim.to_left)
            }
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ProductItemClick {
        fun onProductItemClick(itemsData: CartItems?)
        fun onAddToCartClicked(itemsData: CartItems?, cartQty: String?,isAdd:Int)
    }

    interface CartItemQuantityChangeListener {
        fun onQuantityChanged(cartItem: CartItems, newQuantity: Int)
        fun onDeleteCartItem(cartItem: CartItems)
    }


}