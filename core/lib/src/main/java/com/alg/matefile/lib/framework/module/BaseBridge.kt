package com.alg.matefile.lib.framework.module

import com.alg.matefile.lib.framework.manager.ServiceManager
import com.alg.matefile.lib.framework.service.IService

abstract class BaseBridge<T : IModule> {

  protected var module: T? = null

  init {
    module = getService()?.getBridgeModule()
  }

  protected abstract fun getServiceClass(): Class<out IService>

  @Suppress("UNCHECKED_CAST")
  private fun getService(): IBridgeCenter<T>? {
    val service = ServiceManager.getService(getServiceClass())
    if (service is IBridgeCenter<*>) {
      return service as IBridgeCenter<T>
    }
    return null
  }
}