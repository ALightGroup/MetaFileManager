package com.alg.matefile.lib.framework.domain.manager

import android.app.Application
import com.alg.matefile.lib.framework.domain.IService
import com.alibaba.android.arouter.launcher.ARouter

object ServiceManager {

  fun init(application: Application) {
    ARouter.init(application)
  }

  fun getService(serviceClass: Class<out IService>): IService =
    ARouter.getInstance().build("/file/test").navigation() as IService
}