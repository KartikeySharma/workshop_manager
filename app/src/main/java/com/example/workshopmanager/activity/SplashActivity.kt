package com.example.workshopmanager.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.workshopmanager.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed(
            {
                val intent = Intent(this@SplashActivity, LoginRegisterActivity::class.java)
                finish()
                startActivity(intent)
            }, 1500
        )
    }
}