package com.example.alchilaxo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.alchilaxo.databinding.ActivityHomeBinding
import com.example.alchilaxo.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val a: Animation = AnimationUtils.loadAnimation(this, com.example.alchilaxo.R.anim.anim_img)
        a.fillAfter = true
        a.reset()

        binding.logoimg2.startAnimation(a)


    }

    override fun onRestart() {
        super.onRestart()
        finish()
    }

    private fun getFirstTimeRun(): Int {
        val sp = getSharedPreferences("MYAPP", 0)
        val result: Int
        val currentVersionCode: Int = com.example.alchilaxo.BuildConfig.VERSION_CODE
        val lastVersionCode = sp.getInt("FIRSTTIMERUN", -1)
        result =
            if (lastVersionCode == -1) 0 else if (lastVersionCode == currentVersionCode) 1 else 2
        sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply()
        return result
    }

    /*
    0: la App no se ha ejecutado nunca.
    1: la App alguna vez se ha ejecutado.
    2: la App se ha ejecutado alguna vez, pero no esa versión.
     */


}