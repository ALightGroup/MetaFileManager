package com.alg.matefile.lib.framework.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.getInsetsController
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

abstract class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)
    adaptiveNavigation()
  }

  // 导航栏适配
  private fun adaptiveNavigation() {
    val insetsController: WindowInsetsControllerCompat =
      getInsetsController(window, window.decorView)
    insetsController.isAppearanceLightNavigationBars = true
    insetsController.isAppearanceLightStatusBars = true
    window?.decorView?.let {
      ViewCompat.setOnApplyWindowInsetsListener(it) { view, windowInsets ->
        val bottom = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom
        view.setPadding(0, 0, 0, bottom)
        windowInsets
      }
    }
  }
}