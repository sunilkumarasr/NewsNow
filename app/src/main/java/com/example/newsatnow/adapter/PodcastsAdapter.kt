package com.example.newsatnow.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.example.FeaturedArticles
import com.example.example.LiveNews
import com.example.example.Podcasts
import com.example.newsatnow.R
import com.example.newsatnow.databinding.CatItemsBinding
import com.example.newsatnow.databinding.FutureItemsBinding
import com.example.newsatnow.databinding.ItemTrendingListBinding
import com.example.newsatnow.databinding.ListLiveNewsBinding
import com.example.newsatnow.view.ArticalDetailActivity
import com.example.newsatnow.view.VideoPlayActivity
import com.example.newsatnow.view.YoutubeVideoPlayActivity


class PodcastsAdapter(// List that holds every item to be displayed in RecyclerView
    var texts: ArrayList<Podcasts>
) : RecyclerView.Adapter<PodcastsAdapter.ViewHolder?>() {
    // This function inflated the list_item and fits it into the Recycler View Widget
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ListLiveNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    // This funciton binds the Content with the components of the Recycler View.
    override fun onBindViewHolder(holder: PodcastsAdapter.ViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.binding.liveNewsTitle.text = texts[position].title
        if (texts[position].thumbnail != null) {
            Glide
                .with(context)
                .load(texts[position].thumbnail)
                .transform(CenterCrop(), RoundedCorners(10))
                .placeholder(R.drawable.loading)
                .into(holder.binding.liveNewsImage)
        } else {
            // Handle the null/empty case, e.g., set a default image or hide the ImageView
            holder.binding.liveNewsImage.setImageResource(R.drawable.loading)
        }

        holder.binding.card.setOnClickListener {
            Log.d("Video_url",texts[position].podcastUrl!!)
            if (texts[position].podcastUrl!!.contains("youtube.com/watch", ignoreCase = true)) {
                val parts = texts[position].podcastUrl!!.split("=")
                val intent = Intent(context, YoutubeVideoPlayActivity::class.java)
                intent.putExtra("video_url",parts[1])
                context.startActivity(intent)
            }
            else if (texts[position].podcastUrl!!.contains("youtube.com/embed", ignoreCase = true)){
                val intent = Intent(context, YoutubeVideoPlayActivity::class.java)
                intent.putExtra("video_url",texts[position].podcastUrl!!.replace("https://www.youtube.com/embed/",""))
                context.startActivity(intent)
            }
                else{
                val intent = Intent(context, VideoPlayActivity::class.java)
                intent.putExtra("video_url",texts[position].podcastUrl!!)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int {
        return texts.size
    }

    class ViewHolder(val binding: ListLiveNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}