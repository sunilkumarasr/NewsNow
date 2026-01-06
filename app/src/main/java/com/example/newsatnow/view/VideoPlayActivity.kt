package com.example.newsatnow.view

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityVideoPlayBinding
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.newsatnow.BaseActivity

class VideoPlayActivity : BaseActivity() {

    var binding : ActivityVideoPlayBinding? = null
    private lateinit var exoPlayer: ExoPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding =  ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val videoUrl = intent.extras?.getString("video_url")
        val uri: Uri = videoUrl!!.toUri()
        exoPlayer = ExoPlayer.Builder(this).build()
        binding?.idExoPlayerVIew?.player = exoPlayer

        // Create a MediaItem from the video URL
        val mediaItem = MediaItem.fromUri(uri)

        // Set the media item to the player
        exoPlayer.setMediaItem(mediaItem)

        // Set repeat mode
        exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ALL

        // Prepare the player (loads media)
        exoPlayer.prepare()

        // Automatically start playback when ready
        exoPlayer.playWhenReady = true

    }

    override fun onBackPressed() {
        exoPlayer.release()
        super.onBackPressed()
    }
}