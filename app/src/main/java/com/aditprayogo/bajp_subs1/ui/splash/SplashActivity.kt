package com.aditprayogo.bajp_subs1.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.aditprayogo.bajp_subs1.databinding.ActivitySplashBinding
import com.aditprayogo.bajp_subs1.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val binding : ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler(mainLooper).postDelayed({
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            ).also { finish() }
        },2000)
    }
}