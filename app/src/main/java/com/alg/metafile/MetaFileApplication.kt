package com.alg.metafile

import android.app.Application
import com.alg.matefile.lib.framework.domain.manager.ServiceManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MetaFileApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    ServiceManager.init(this)
  }
}