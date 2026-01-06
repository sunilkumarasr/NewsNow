package com.example.newsatnow.view

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityArticalDetailBinding
import com.example.newsatnow.viewModel.ArticalDetailViewModel
import androidx.core.net.toUri


class ArticalDetailActivity : BaseActivity() {
    var binding: ActivityArticalDetailBinding? = null
    lateinit var mainActivityViewModel: ArticalDetailViewModel
    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityArticalDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val id = intent.extras?.getString("id")

        mainActivityViewModel = ViewModelProvider(this)[ArticalDetailViewModel::class.java]


        mainActivityViewModel.getArticalDetail(id)!!.observe(this, Observer { serviceSetterGetter ->
            binding?.progressBar?.visibility = View.GONE
            Glide
                .with(this)
                .load(serviceSetterGetter.article?.image)
                .placeholder(R.drawable.loading)
                .into(binding?.articalImage!!)
            binding?.articalTitle?.text = serviceSetterGetter.article?.title

            binding?.articalTime?.text = serviceSetterGetter.article?.createdAt
            binding?.articalDesription?.text = Html.fromHtml(serviceSetterGetter.article?.summary)
            binding?.like?.text = serviceSetterGetter.article?.likes.toString()
            binding?.dislike?.text = serviceSetterGetter.article?.dislikes.toString()
            binding?.comment?.text = buildString {
                append(serviceSetterGetter.article?.commentsCount.toString())
                append(" Comments")
            }
            binding?.articalTitle?.setOnClickListener {

                val intent = Intent(this, WebviewActivity::class.java)
                intent.putExtra("source", serviceSetterGetter.article?.source)
                startActivity(intent)
            }
            if(serviceSetterGetter.article?.video == null){
                binding?.playIcon?.visibility = View.GONE
            }else{
                binding?.playIcon?.visibility = View.VISIBLE
            }
            binding?.playIcon?.setOnClickListener {
                if (serviceSetterGetter.article?.video?.toString() == "")
                    return@setOnClickListener
                else {
                    if (serviceSetterGetter.article?.video?.contains("youtube", ignoreCase = true) == true) {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            serviceSetterGetter.article?.video?.toUri()
                        )
                        intent.setComponent(
                            ComponentName(
                                "com.google.android.youtube",
                                "com.google.android.youtube.PlayerActivity"
                            )
                        )

                        val manager: PackageManager = packageManager
                        val infos = manager.queryIntentActivities(intent, 0)
                        if (infos.size > 0) {
                            startActivity(intent)
                        } else {
                            //No Application can handle your intent
                        }
                    } else {
                        if (serviceSetterGetter.article?.video!!.contains("youtube", ignoreCase = true)) {
                            val parts = serviceSetterGetter.article?.video!!.split("=")
                            val intent = Intent(this, YoutubeVideoPlayActivity::class.java)
                            intent.putExtra("video_url",parts[1])
                            startActivity(intent)
                        }else if (serviceSetterGetter.article?.video!!.contains("youtube.com/embed", ignoreCase = true)){
                            val intent = Intent(this, YoutubeVideoPlayActivity::class.java)
                            intent.putExtra("video_url",serviceSetterGetter.article?.video!!.replace("https://www.youtube.com/embed/",""))
                            startActivity(intent)
                        }else{
                            val intent = Intent(this, VideoPlayActivity::class.java)
                            intent.putExtra("video_url",serviceSetterGetter.article?.video!!)
                            startActivity(intent)
                        }
                    }
                }
            }
        })


        binding?.backArrow?.setOnClickListener {
            finish()
        }


    }
}