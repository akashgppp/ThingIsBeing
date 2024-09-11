package com.skysoftsolution.thingisbeing

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.skysoftsolution.thingisbeing.dashboard.DashBoardActivity
import com.skysoftsolution.thingisbeing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        // Apply fullscreen flags
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                )

        // For Android 11+ devices, you can use WindowInsetsController for more control
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.insetsController?.apply {
                hide(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE)
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
       */
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#064369")
        Glide.with(this)
            .asGif()
            .load(R.drawable.splashscreen)  // Your GIF file in res/drawable
            .into(binding.imageView)

        setUi()

    }
    private fun setUi() {
        Handler(Looper.getMainLooper()).postDelayed({
            var intent: Intent? = null
            intent = Intent(this@MainActivity, DashBoardActivity::class.java)

            startActivity(intent)
            finish()
        }, 5000)
    }
}