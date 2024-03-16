package com.example.salaryfxnitap

import com.example.salaryfxnitap.LoginActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.example.salaryfxnitap.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var  splashBinding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding= ActivityWelcomeBinding.inflate(layoutInflater)
        val view  =splashBinding.root
        setContentView(view)

        val alphaAnimation =AnimationUtils.loadAnimation(applicationContext,R.anim.splash_anim)
        splashBinding.textviewSplash.startAnimation(alphaAnimation)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object :Runnable{
            override fun run() {
                val intent = Intent(this@WelcomeActivity,LoginActivity::class.java)
                startActivity(intent)

            }

        },5000)
    }
}