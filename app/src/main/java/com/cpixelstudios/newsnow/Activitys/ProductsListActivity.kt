package com.cpixelstudios.newsnow.Activitys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.cpixelstudios.newsnow.Adapters.Cart.CartItems
import com.cpixelstudios.newsnow.Adapters.Cart.CartListResponse
import com.cpixelstudios.newsnow.Adapters.ProductsAdapter
import com.cpixelstudios.newsnow.Api.RetrofitClient
import com.cpixelstudios.newsnow.Config.Preferences
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.Models.AddtoCartResponse
import com.cpixelstudios.newsnow.Models.CategoryWiseModel
import com.cpixelstudios.newsnow.Models.CategoryWiseResponse
import com.cpixelstudios.newsnow.Models.UpdateCartResponse
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.ActivityProductsListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsListActivity : AppCompatActivity(), ProductsAdapter.ProductItemClick,
    ProductsAdapter.CartItemQuantityChangeListener {

    lateinit var cartItemQuantityChangeListener: ProductsAdapter.CartItemQuantityChangeListener
    lateinit var productItemClick: ProductsAdapter.ProductItemClick

    val binding: ActivityProductsListBinding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    //Products list
    var productList: List<CategoryWiseResponse> = ArrayList()
    var cartItemsList: List<CartItems> = ArrayList()



    var categoriesId: String = ""
    var categoryName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        categoriesId = intent.getStringExtra("categoriesId").toString()
        categoryName = intent.getStringExtra("categoryName").toString()

        inIts()

    }

    private fun inIts() {
        cartItemQuantityChangeListener = this@ProductsListActivity
        productItemClick = this@ProductsListActivity

        binding.imgBack.setOnClickListener {
            val animations = ViewController.animation()
            binding.imgBack.startAnimation(animations)
            finish()
            overridePendingTransition(R.anim.from_left, R.anim.to_right)
        }

        binding.linearCart.setOnClickListener {
            val animations = ViewController.animation()
            binding.imgBack.startAnimation(animations)
            val intent = Intent(this@ProductsListActivity, CartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }

        binding.txtTitle.text = categoryName


        if (!ViewController.noInterNetConnectivity(applicationContext)) {
            ViewController.showToast(applicationContext, "Please check your connection ")
        } else {
            getCategoryWiseProductsListApi()
        }


    }

    //list
    private fun getCategoryWiseProductsListApi() {
        binding.progressBar.visibility = View.VISIBLE
        val apiServices = RetrofitClient.apiInterface
        val call =
            apiServices.getCategoryWiseProductsListApi(
                getString(R.string.api_key),
                categoriesId,
            )
        call.enqueue(object : Callback<CategoryWiseModel> {
            override fun onResponse(
                call: Call<CategoryWiseModel>,
                response: Response<CategoryWiseModel>
            ) {
                binding.progressBar.visibility = View.GONE
                try {
                    if (response.isSuccessful) {
                        productList = response.body()?.response!!
                        if (productList.size > 0) {
                            binding.recyclerview.visibility = View.VISIBLE
                            binding.linearNoData.visibility = View.GONE

                            getCartApi()
                        } else {
                            binding.recyclerview.visibility = View.GONE
                            binding.linearNoData.visibility = View.VISIBLE
                        }
                    } else {
                        binding.recyclerview.visibility = View.GONE
                        binding.linearNoData.visibility = View.VISIBLE
                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                    Log.e("onFailure",e.message.toString())
                    binding.recyclerview.visibility = View.GONE
                    binding.linearNoData.visibility = View.VISIBLE
                }
            }
            override fun onFailure(call: Call<CategoryWiseModel>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.recyclerview.visibility = View.GONE
                binding.linearNoData.visibility = View.VISIBLE
                Log.e("onFailure",t.message.toString())
            }
        })

    }
    private fun getCartApi() {
        binding.progressBar.visibility = View.VISIBLE
        val userId = Preferences.loadStringValue(applicationContext, Preferences.userId, "")
        val apiServices = RetrofitClient.apiInterface
        val call = apiServices.getCartApi(
                getString(R.string.api_key),
                userId.toString(),
            )
        call.enqueue(object : Callback<CartListResponse> {
            override fun onResponse(
                call: Call<CartListResponse>,
                response: Response<CartListResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                try {
                    if (response.isSuccessful) {
                        cartItemsList = response.body()?.ResponseCartList!!

                        DataSet()
                    } else {
                        DataSet()
                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                    Log.e("onFailure",e.message.toString())
                    DataSet()
                }
            }
            override fun onFailure(call: Call<CartListResponse>, t: Throwable) {
                Log.e("onFailure",t.message.toString())
                DataSet()
            }
        })
    }
    private fun DataSet() {

        //empty list
        val cartListToPass = if (cartItemsList.isNullOrEmpty()) arrayListOf() else cartItemsList

        binding.recyclerview.layoutManager = LinearLayoutManager(this@ProductsListActivity)
        binding.recyclerview.adapter = ProductsAdapter(this@ProductsListActivity, productList,cartListToPass, this@ProductsListActivity, cartItemQuantityChangeListener)
//            val intent = Intent(this@CartActivity, ProductsDetailsActivity::class.java).apply {
//                putExtra("productsId", item.products_id)
//            }
//            startActivity(intent)
//            overridePendingTransition(R.anim.from_right, R.anim.to_left)
    }


    override fun onProductItemClick(itemsData: CategoryWiseResponse?) {

    }
    override fun onAddToCartClicked(itemsData: CategoryWiseResponse?, cartQty: String?, isAdd: Int) {
        if (isAdd == 0) {
            if (!ViewController.noInterNetConnectivity(applicationContext)) {
                ViewController.showToast(applicationContext, "Please check your connection ")
            } else {
                addToCart(itemsData, cartQty)
            }
        } else{
            if (!ViewController.noInterNetConnectivity(applicationContext)) {
                ViewController.showToast(applicationContext, "Please check your connection ")
            } else {
                updateCart(itemsData, cartQty)
            }
        }
    }

    private fun addToCart(itemsData: CategoryWiseResponse?, cartQty: String?) {
        binding.progressBar.visibility = View.VISIBLE
        val userId = Preferences.loadStringValue(applicationContext, Preferences.userId, "")
        val apiServices = RetrofitClient.apiInterface
        val call =
            apiServices.addToCartApi(
                getString(R.string.api_key),
                userId.toString(),
                itemsData?.productsId.toString(),
                "1"
            )
        call.enqueue(object : Callback<AddtoCartResponse> {
            override fun onResponse(
                call: Call<AddtoCartResponse>,
                response: Response<AddtoCartResponse>
            ) {
                binding.progressBar.visibility = View.GONE
                if (!ViewController.noInterNetConnectivity(applicationContext)) {
                    ViewController.showToast(applicationContext, "Please check your connection ")
                } else {
                    getCategoryWiseProductsListApi()
                }
                try {
                    if (response.isSuccessful) {
                        val res = response.body()
                        if (res != null) {
                            if (res.message.equals("Success")){

                            }else{
                                ViewController.showToast(this@ProductsListActivity,res.message)
                            }
                        }
                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                    Log.e("onFailure",e.message.toString())
                }
            }
            override fun onFailure(call: Call<AddtoCartResponse>, t: Throwable) {
                Log.e("onFailure",t.message.toString())
                binding.progressBar.visibility = View.GONE
                if (!ViewController.noInterNetConnectivity(applicationContext)) {
                    ViewController.showToast(applicationContext, "Please check your connection ")
                } else {
                    getCategoryWiseProductsListApi()
                }
            }
        })

    }

    private fun updateCart(itemsData: CategoryWiseResponse?, cartQty: String?) {
        binding.progressBar.visibility = View.VISIBLE
        val userId = Preferences.loadStringValue(applicationContext, Preferences.userId, "")
        val apiServices = RetrofitClient.apiInterface
        val call =
            apiServices.upDateCartApi(
                getString(R.string.api_key),
                userId.toString(),
                itemsData?.productsId.toString(),
                cartQty!!,
            )
        call.enqueue(object : Callback<UpdateCartResponse> {
            override fun onResponse(
                call: Call<UpdateCartResponse>,
                response: Response<UpdateCartResponse>
            ) {
                binding.progressBar.visibility = View.GONE

                if (!ViewController.noInterNetConnectivity(applicationContext)) {
                    ViewController.showToast(applicationContext, "Please check your connection ")
                } else {
                    getCategoryWiseProductsListApi()
                }
                try {
                    if (response.isSuccessful) {
                        val res = response.body()
                        if (res != null) {
                            if (res.message.equals("Success")){

                            }else{
                                ViewController.showToast(this@ProductsListActivity,res.message)
                            }
                        }
                    }
                } catch (e: NullPointerException) {
                    e.printStackTrace()
                    Log.e("onFailure",e.message.toString())
                }
            }
            override fun onFailure(call: Call<UpdateCartResponse>, t: Throwable) {
                Log.e("onFailure",t.message.toString())
                binding.progressBar.visibility = View.GONE
                if (!ViewController.noInterNetConnectivity(applicationContext)) {
                    ViewController.showToast(applicationContext, "Please check your connection ")
                } else {
                    getCategoryWiseProductsListApi()
                }
            }
        })

    }

    override fun onQuantityChanged(cartItem: CategoryWiseResponse, newQuantity: Int) {
//        val index = cartItemsList.indexOfFirst { it.product_id == cartItem.product_id }
//        if (index != -1) {
//            cartItemsList[index].cart_quantity = newQuantity.toString() // Now it's mutable
//            getTotalPrice(cartItemsList)
//        }
    }

    //delete item in cart
    override fun onDeleteCartItem(cartItem: CategoryWiseResponse) {

    }



    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
    }

}