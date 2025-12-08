package com.example.newsatnow.adapter

import android.content.Intent
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.TrendingArticles
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ItemTrendingSlidesBinding
import com.example.newsatnow.databinding.SlideItemBinding
import com.example.newsatnow.view.Logins.LoginActivity
import com.example.newsatnow.view.VideoPlayActivity
import com.example.newsatnow.view.WebviewActivity
import com.example.newsatnow.view.YoutubeVideoPlayActivity

class TrendingOnboardingAdapter(trendingArticles: ArrayList<TrendingArticles>) : RecyclerView.Adapter<TrendingOnboardingAdapter.ViewHolder?>() {
    val trendingList = trendingArticles

    class ViewHolder(val binding: ItemTrendingSlidesBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemTrendingSlidesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val context = holder.itemView.context
        Glide
            .with(context)
            .load(trendingList[position].image)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(holder.binding.trendingImage)
        Glide
            .with(context)
            .load(trendingList[position].image)
            .centerCrop()
            .placeholder(R.drawable.loading)
            .into(holder.binding.trendingImageBackground)
            holder.binding.description.text = Html.fromHtml(trendingList[position].summary)
            holder.binding.trendingNewsTitle.text = Html.fromHtml(trendingList[position].title)
            holder.binding.trendingNewsTitle.setOnClickListener {

            val intent = Intent(context, WebviewActivity::class.java)
            intent.putExtra("source", trendingList[position].source)
            context.startActivity(intent)
        }
        if (trendingList[position].video != null) {
            holder.binding.playIcon.visibility = View.VISIBLE
        } else {
            holder.binding.playIcon.visibility = View.GONE
        }
        holder.binding.playIcon.setOnClickListener {
            Log.d("Video_url",trendingList[position].video!!)
            if (trendingList[position].video!!.contains("youtube.com/watch", ignoreCase = true)) {
                val parts = trendingList[position].video!!.split("=")
                val intent = Intent(context, YoutubeVideoPlayActivity::class.java)
                intent.putExtra("video_url",parts[1])
                context.startActivity(intent)
            }
            else if (trendingList[position].video!!.contains("youtube.com/embed", ignoreCase = true)){
                val intent = Intent(context, YoutubeVideoPlayActivity::class.java)
                intent.putExtra("video_url",trendingList[position].video!!.replace("https://www.youtube.com/embed/",""))
                context.startActivity(intent)
            }
            else{
                val intent = Intent(context, VideoPlayActivity::class.java)
                intent.putExtra("video_url",trendingList[position].video!!)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount() = trendingList.size
}