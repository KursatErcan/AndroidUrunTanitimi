package com.kursat.gittigeliyor.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.kursat.gittigeliyor.R
import java.util.*

// TODO : İnternet kontrolü yap
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                startActivity(Intent(applicationContext, LoginActivity::class.java))
                finish()
            }
        }, 3000)
    }
}