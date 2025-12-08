package com.example.newsatnow.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.example.FeaturedArticles
import com.example.example.InterestsCategories
import com.example.newsatnow.R
import com.example.newsatnow.databinding.CatItemsBinding
import com.example.newsatnow.databinding.FutureItemsBinding
import com.example.newsatnow.databinding.ItemInterestsBinding
import com.example.newsatnow.view.ArticalDetailActivity


class InterestsAdapter(// List that holds every item to be displayed in RecyclerView
    var texts: ArrayList<InterestsCategories>
) : RecyclerView.Adapter<InterestsAdapter.ViewHolder?>() {
    // This function inflated the list_item and fits it into the Recycler View Widget
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemInterestsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    // This funciton binds the Content with the components of the Recycler View.
    override fun onBindViewHolder(holder: InterestsAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.interestsText.text = texts[position].name
        Glide
            .with(context)
            .load(texts[position].icon)
            .transform(CenterCrop(), RoundedCorners(1))
            .placeholder(R.drawable.loading)
            .error(R.drawable.loading)
            .into(holder.binding.interestImage)

    }

    override fun getItemCount(): Int {
        return texts.size
    }

    class ViewHolder(val binding: ItemInterestsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}