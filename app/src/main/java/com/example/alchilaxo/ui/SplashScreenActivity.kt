package com.example.alchilaxo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.alchilaxo.R
import com.example.alchilaxo.databinding.ActivitySplashScreenBinding
import android.content.SharedPreferences
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.back.alpha=0.5f
        val a: Animation = AnimationUtils.loadAnimation(this, com.example.alchilaxo.R.anim.anim_img)
        a.fillAfter = true
        a.reset()

        binding.logo.startAnimation(a)


        binding.back.animate().setDuration(3000).alpha(1f).withEndAction{
            Toast.makeText(applicationContext, getFirstTimeRun().toString(), Toast.LENGTH_LONG).show()
            if (getFirstTimeRun() == 0){

                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        }


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