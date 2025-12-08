package com.example.newsatnow.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.example.TrendingArticles
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ItemTrendingListBinding
import com.example.newsatnow.databinding.TrendingItemsBinding
import com.example.newsatnow.view.ArticalDetailActivity
import com.example.newsatnow.view.VideoPlayActivity
import com.example.newsatnow.view.YoutubeVideoPlayActivity


class TrendingListAdapter(// List that holds every item to be displayed in RecyclerView
    var texts: ArrayList<TrendingArticles>
) : RecyclerView.Adapter<TrendingListAdapter.ViewHolder?>() {
    // This function inflated the list_item and fits it into the Recycler View Widget
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemTrendingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    // This funciton binds the Content with the components of the Recycler View.
    override fun onBindViewHolder(holder: TrendingListAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.newsTitle.text = texts[position].title
        holder.binding.catagery.text = texts[position].category?.name
        Glide
            .with(context)
            .load(texts[position].image)
            .transform(CenterCrop(), RoundedCorners(10))
            .placeholder(R.drawable.loading)
            .into(holder.binding.trendingImage)
        holder.binding.time.text = texts[position].updatedAt
        holder.binding.comments.text = buildString {
            append(texts[position].commentsCount.toString())
            append(" Comments")
        }
        if (texts[position].video != null) {
            holder.binding.videoPlay.visibility = View.VISIBLE
        } else {
            holder.binding.videoPlay.visibility = View.GONE
        }
        holder.binding.videoPlay.setOnClickListener {
            Log.d("Video_url",texts[position].video!!)
            if (texts[position].video!!.contains("youtube.com/watch", ignoreCase = true)) {
                val parts = texts[position].video!!.split("=")
                val intent = Intent(context, YoutubeVideoPlayActivity::class.java)
                intent.putExtra("video_url",parts[1])
                context.startActivity(intent)
            }
            else if (texts[position].video!!.contains("youtube.com/embed", ignoreCase = true)){
                val intent = Intent(context, YoutubeVideoPlayActivity::class.java)
                intent.putExtra("video_url",texts[position].video!!.replace("https://www.youtube.com/embed/",""))
                context.startActivity(intent)
            }
            else{
                val intent = Intent(context, VideoPlayActivity::class.java)
                intent.putExtra("video_url",texts[position].video!!)
                context.startActivity(intent)
            }
        }
        holder.binding.card.setOnClickListener {
            val intent = Intent(context, ArticalDetailActivity::class.java)
            intent.putExtra("id", texts[position].id.toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return texts.size
    }

    class ViewHolder(val binding: ItemTrendingListBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}