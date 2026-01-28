package com.step.counter.core.utils.extensions

import android.app.Activity
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun Activity.setInsets(view: View) {
    ViewCompat.setOnApplyWindowInsetsListener(view) { v, windowInsets ->
        val systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.updatePadding(bottom = systemBars.bottom)
        WindowInsetsCompat.CONSUMED
    }
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.isAppearanceLightStatusBars = true
    windowInsetsController.isAppearanceLightNavigationBars = true
}