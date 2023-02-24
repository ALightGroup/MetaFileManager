package com.alg.metafile

import android.app.Application
import com.alg.matefile.lib.framework.manager.ServiceManager

class MetaFileApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    ServiceManager.init(this)
  }
}