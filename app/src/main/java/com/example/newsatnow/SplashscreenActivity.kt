package com.example.newsatnow

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.WindowCompat
import com.example.newsatnow.Config.Preferences
import com.example.newsatnow.view.DashBoardActivity
import com.example.newsatnow.view.SlidesActivity

class SplashscreenActivity : BaseActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        setContentView(R.layout.activity_splashscreen)
        redirectToMainScreen()

    }

    private fun redirectToMainScreen() {
        LogoAnimation()

        val loginCheck = Preferences.loadStringValue(applicationContext, Preferences.LOGINCHECK, "")


        Handler(mainLooper).postDelayed({
            if (loginCheck.equals("Open")) {
                startActivity(
                    Intent(
                        this,
                        DashBoardActivity::class.java
                    ).apply { Intent.FLAG_ACTIVITY_SINGLE_TOP })
                finish()
            } else {
                startActivity(
                    Intent(
                        this,
                        SlidesActivity::class.java
                    ).apply { Intent.FLAG_ACTIVITY_SINGLE_TOP })
                finish()
            }

        }, 3000)

    }

    private fun LogoAnimation() {
        val splashLogo: ImageView = findViewById(R.id.imgLogo)

        splashLogo.pivotX = splashLogo.width / 2f
        splashLogo.pivotY = splashLogo.height / 2f

        val scaleX = ObjectAnimator.ofFloat(splashLogo, "scaleX", 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(splashLogo, "scaleY", 0f, 1f)
        val fadeIn = ObjectAnimator.ofFloat(splashLogo, "alpha", 0f, 1f)
        val moveUp = ObjectAnimator.ofFloat(splashLogo, "translationY", 50f, 0f) // smaller offset

        scaleX.duration = 1000
        scaleY.duration = 1000
        fadeIn.duration = 1000
        moveUp.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleX, scaleY, fadeIn, moveUp)
        animatorSet.start()

    }


    override fun onResume() {
        super.onResume()
        redirectToMainScreen()
    }


}