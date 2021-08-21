package com.example.alchilaxo.core.util

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// Logger

fun log(message: String) {
    Log.i("AlChilaxo", message)
}

// Navigation

fun View.GotoNavigate(action: Int, arg: Bundle? = null, activity: FragmentActivity) {
    Navigation.findNavController(this)
        .navigate(action, arg)
    activity.stopInteraction()
}

fun FragmentActivity.stopInteraction() {
    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
}

fun FragmentActivity.resumeInteraction() {
    Handler().postDelayed({
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }, 500)
}

// Configure simple vertical or horizontal RecyclerView

fun <T> RecyclerView.configureRecycler(adapter: DynamicAdapter<T>, isVertical: Boolean = true, isgrid: Boolean = false, column: Int = 3) {
    itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

    layoutManager = if (isgrid) {
        androidx.recyclerview.widget.GridLayoutManager(context, column)
    }
    else {
        androidx.recyclerview.widget.LinearLayoutManager(context,
            if (isVertical) RecyclerView.VERTICAL else RecyclerView.HORIZONTAL, false)
    }

    this.adapter = adapter
}


fun FragmentActivity.launch(action: suspend () -> Unit) {
    lifecycleScope.launch {
        withContext(Dispatchers.Main) {
            action.invoke()
        }
    }
}

