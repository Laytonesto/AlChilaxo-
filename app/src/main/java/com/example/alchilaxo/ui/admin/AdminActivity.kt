package com.example.alchilaxo.ui.admin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.alchilaxo.R
import com.example.alchilaxo.ui.admin.ui.main.AdminFragment

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AdminFragment.newInstance())
                .commitNow()
        }
    }
}