package com.counter.step

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@MainActivity, com.step.counter.MainActivity::class.java))
        }, 2000)
    }
}
