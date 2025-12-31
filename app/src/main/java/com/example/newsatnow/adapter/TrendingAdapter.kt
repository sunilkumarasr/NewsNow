package com.example.newsatnow.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.example.TrendingArticles
import com.example.newsatnow.R
import com.example.newsatnow.databinding.TrendingItemsBinding
import com.example.newsatnow.view.ArticalDetailActivity
import com.example.newsatnow.view.VideoPlayActivity
import com.example.newsatnow.view.YoutubeVideoPlayActivity


class TrendingAdapter(
    private val originalList: ArrayList<TrendingArticles>
) : RecyclerView.Adapter<TrendingAdapter.ViewHolder>(), Filterable {

    private var filteredList = ArrayList<TrendingArticles>(originalList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        TrendingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = filteredList[position]

        holder.binding.newsTitle.text = item.title
        holder.binding.catagery.text = item.category?.name

        Glide.with(context)
            .load(item.image)
            .transform(CenterCrop(), RoundedCorners(10))
            .placeholder(R.drawable.loading)
            .into(holder.binding.trendingImage)

        holder.binding.time.text = item.updatedAt
        holder.binding.comments.text = "${item.commentsCount} Comments"

        holder.binding.videoPlay.visibility =
            if (item.video != null) View.VISIBLE else View.GONE

        holder.binding.videoPlay.setOnClickListener {
            Log.d("Video_url", item.video!!)
            if (item.video!!.contains("youtube.com/watch", true)) {
                val parts = item.video!!.split("=")
                context.startActivity(
                    Intent(context, YoutubeVideoPlayActivity::class.java)
                        .putExtra("video_url", parts[1])
                )
            } else if (item.video!!.contains("youtube.com/embed", true)) {
                context.startActivity(
                    Intent(context, YoutubeVideoPlayActivity::class.java)
                        .putExtra(
                            "video_url",
                            item.video!!.replace("https://www.youtube.com/embed/", "")
                        )
                )
            } else {
                context.startActivity(
                    Intent(context, VideoPlayActivity::class.java)
                        .putExtra("video_url", item.video!!)
                )
            }
        }

        holder.binding.card.setOnClickListener {
            context.startActivity(
                Intent(context, ArticalDetailActivity::class.java)
                    .putExtra("id", item.id.toString())
            )
        }
    }

    override fun getItemCount(): Int = filteredList.size

    // 🔍 SEARCH FILTER
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val query = constraint.toString().lowercase().trim()
                filteredList = if (query.isEmpty()) {
                    ArrayList(originalList)
                } else {
                    originalList.filter {
                        it.title?.lowercase()?.contains(query) == true ||
                                it.category?.name?.lowercase()?.contains(query) == true
                    } as ArrayList<TrendingArticles>
                }

                return FilterResults().apply { values = filteredList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results?.values as ArrayList<TrendingArticles>
                notifyDataSetChanged()
            }
        }
    }

    class ViewHolder(val binding: TrendingItemsBinding) :
        RecyclerView.ViewHolder(binding.root)
}
