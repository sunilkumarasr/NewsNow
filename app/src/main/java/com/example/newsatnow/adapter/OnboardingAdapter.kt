package com.example.newsatnow.adapter

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.activity.compose.LocalActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivitySlidesBinding
import com.example.newsatnow.databinding.SlideItemBinding
import com.example.newsatnow.view.Logins.CreateAccountActivity
import com.example.newsatnow.view.Logins.LoginActivity
import com.example.newsatnow.view.MainActivity
import kotlinx.coroutines.NonDisposableHandle.parent

class OnboardingAdapter(
    contex: ActivitySlidesBinding,
    arrayList: Array<Int>,
    arrayListTitle: Array<String>,
    arrayListSubTitle: Array<String>
) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder?>() {
    val arrayListOfImages = arrayList
    val arrayListOfTitle = arrayListTitle
    val arrayListOfSubTitle = arrayListSubTitle
    class ViewHolder(val binding: SlideItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        SlideItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {
                binding.skip.setOnClickListener {
                    val context = holder.itemView.context
                    val intent = Intent(context, LoginActivity::class.java)
                    context.startActivity(intent)
                }
            }
            val context = holder.itemView.context
            binding.imgIntro.setImageResource(arrayListOfImages[position])
            binding.mainTitle.text = arrayListOfTitle[position]
            binding.subTitle.text = arrayListOfSubTitle[position]
            if (position == 3) {
                binding.titleContentText.visibility = ViewGroup.VISIBLE
                binding.skip.visibility = ViewGroup.GONE
                binding.loginButton.visibility = ViewGroup.VISIBLE
            }else{
                binding.titleContentText.visibility = ViewGroup.GONE
                binding.skip.visibility = ViewGroup.VISIBLE
                binding.loginButton.visibility = ViewGroup.GONE
            }

            //binding.imgIntro.startAnimation(animationTopToBottom)
            binding.loginButton.setOnClickListener {
                val animations = ViewController.animation()
                binding.loginButton.startAnimation(animations)

                val context = holder.itemView.context
                val intent = Intent(context, LoginActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount() = 4
}