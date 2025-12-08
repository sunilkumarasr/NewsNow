package com.example.newsatnow.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityYoutubeVideoPlayBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class YoutubeVideoPlayActivity : AppCompatActivity() {
    var binding: ActivityYoutubeVideoPlayBinding? = null
    var videoID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeVideoPlayBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        val videoUrl = intent.extras?.getString("video_url")
        videoID = videoUrl!!
        // on below line we are adding listener
        // for our youtube player view.
        binding?.youTubePlayerView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                // loading the selected video
                // into the YouTube Player
                youTubePlayer.loadVideo(videoID, 0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                // this method is called if video has ended,
                super.onStateChange(youTubePlayer, state)
            }
        })

    }
}