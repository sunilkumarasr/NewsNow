package com.cpixelstudios.newsnow.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.models.SlideModel
import com.cpixelstudios.newsnow.Activitys.ProductsDetailsActivity
import com.cpixelstudios.newsnow.Activitys.ProductsListActivity
import com.cpixelstudios.newsnow.Activitys.SearchActivity
import com.cpixelstudios.newsnow.Adapters.HomeCategoriesAdapter
import com.cpixelstudios.newsnow.Adapters.HomeProductsAdapter
import com.cpixelstudios.newsnow.Api.RetrofitClient
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.Models.CategoryListResponse
import com.cpixelstudios.newsnow.Models.CategoryModel
import com.cpixelstudios.newsnow.Models.HomeProductsModel
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Products
    private lateinit var homeProductsAdapter: HomeProductsAdapter
    private lateinit var productsList: ArrayList<HomeProductsModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        if (!ViewController.noInterNetConnectivity(requireActivity())) {
            ViewController.showToast(requireActivity(), "Please check your connection ")
            return
        } else {
            HomebannersApi()
            getCategoriesApi()
            HomeProductsApi()
        }

        binding.viewMoreProducts.setOnClickListener {
            val animations = ViewController.animation()
            binding.viewMoreProducts.startAnimation(animations)
            val intent = Intent(activity, ProductsListActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }

        binding.linearSearch.setOnClickListener {
            val animations = ViewController.animation()
            binding.linearSearch.startAnimation(animations)
            val intent = Intent(activity, SearchActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }


    }

    private fun HomebannersApi() {
        val imageList = mutableListOf<SlideModel>()
        imageList.add(SlideModel(R.drawable.dummy_banner, ""))
        imageList.add(SlideModel(R.drawable.dummy_banner, ""))
        imageList.add(SlideModel(R.drawable.dummy_banner, ""))
        binding.imageSlider.setImageList(imageList)
    }

    private fun getCategoriesApi() {
        val apiServices = RetrofitClient.apiInterface
        val call = apiServices.getCategoriesApi(getString(R.string.api_key))
        call.enqueue(object : Callback<CategoryModel> {
            override fun onResponse(call: Call<CategoryModel>, response: Response<CategoryModel>) {
                try {
                    if (response.isSuccessful) {
                        val selectedServicesList = response.body()?.response
                        //empty
                        if (selectedServicesList.isNullOrEmpty()) {
                            binding.txtCatHeader.visibility = View.GONE
                            binding.recyclerViewCategories.visibility = View.GONE
                            return
                        }
                        DataSet(selectedServicesList)

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.e("onResponseException", e.message.toString())
                }
            }
            override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                Log.e("onFailureCategoryModel", "API Call Failed: ${t.message}")
            }
        })
    }
    private fun DataSet(selectedServicesList: List<CategoryListResponse>) {
        // Truncate the list to the first 6 items if needed
       // val truncatedList = selectedServicesList.take(6)
        binding.recyclerViewCategories.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerViewCategories.adapter  = HomeCategoriesAdapter(selectedServicesList) { item ->
                val intent = Intent(activity, ProductsListActivity::class.java).apply {
                    putExtra("categoriesId", item.categoriesId)
                    putExtra("categoryName", item.categoryName)
                }
                startActivity(intent)
                requireActivity().overridePendingTransition(R.anim.from_right, R.anim.to_left)
            }
        }
    }

    private fun HomeProductsApi() {

        // Populate the static list with data
        productsList = ArrayList()
        productsList.add(HomeProductsModel(R.drawable.beets_ic, "Tomato", "₹800","",4))
        productsList.add(HomeProductsModel(R.drawable.califlower_ic, "Cabbage", "₹400","",4))
        productsList.add(HomeProductsModel(R.drawable.green_leafy_ic, "Bangala", "₹500","",4))
        productsList.add(HomeProductsModel(R.drawable.beets_ic, "Capsicum", "₹800","",4))
        productsList.add(HomeProductsModel(R.drawable.califlower_ic, "Mirchi", "₹200","",4))
        productsList.add(HomeProductsModel(R.drawable.beets_ic, "Onion", "₹900","",4))
        productsList.add(HomeProductsModel(R.drawable.califlower_ic, "Carrot", "₹300","",4))

        // Set the adapter
        binding.recyclerViewProducts.layoutManager = GridLayoutManager(activity, 2)
        homeProductsAdapter = HomeProductsAdapter(productsList){ selectedItem ->
            val intent = Intent(requireActivity(), ProductsDetailsActivity::class.java)
            startActivity(intent)
            requireActivity().overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }

        binding.recyclerViewProducts.adapter = homeProductsAdapter
        binding.recyclerViewProducts.setHasFixedSize(true)

    }


}