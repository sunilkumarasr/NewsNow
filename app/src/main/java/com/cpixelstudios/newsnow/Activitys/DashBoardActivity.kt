package com.cpixelstudios.newsnow.Activitys

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.cpixelstudios.newsnow.Config.Preferences
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.ActivityDashBoardBinding

class DashBoardActivity : AppCompatActivity() {

    val binding: ActivityDashBoardBinding by lazy {
        ActivityDashBoardBinding.inflate(layoutInflater)
    }

    //bottom menu
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(
            this,
            ContextCompat.getColor(this, R.color.colorPrimary),
            false
        )

        inIts()

    }

    private fun inIts() {

        Preferences.saveStringValue(this@DashBoardActivity, Preferences.LOGINCHECK, "Login")

        bottomMenu()

        binding.linearCart.setOnClickListener {
            val animations = ViewController.animation()
            binding.linearCart.startAnimation(animations)
            val intent = Intent(this@DashBoardActivity, CartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_right, R.anim.to_left)
        }

    }

    private fun bottomMenu() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = findNavController(R.id.frame_layout)
        // Setup Bottom Navigation
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.home -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.favorite -> {
                    navController.navigate(R.id.favouriteFragment)
                    true
                }

                R.id.orders -> {
                    navController.navigate(R.id.ordersFragment)
                    true
                }

                R.id.menu -> {
                    navController.navigate(R.id.menuFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        exitDialog()
    }

    private fun exitDialog() {
        val dialogBuilder = AlertDialog.Builder(this@DashBoardActivity)
        dialogBuilder.setTitle("Exit")
        dialogBuilder.setMessage("Are you sure want to exit this app?")
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            finishAffinity()
            dialog.dismiss()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
        val b = dialogBuilder.create()
        b.show()
    }


}