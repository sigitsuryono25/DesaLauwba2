package com.lauwba.project.desalauwba.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lauwba.project.desalauwba.R

class SplashActivity : AppCompatActivity() {

    lateinit var handler: Handler;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        handler = Handler()

        handler.postDelayed(Runnable {
            var intent = intent
            intent = Intent(this@SplashActivity,HomeActivity::class.java)
            startActivity(intent)
            this@SplashActivity.finish()
        }, 2000)





    }

}
