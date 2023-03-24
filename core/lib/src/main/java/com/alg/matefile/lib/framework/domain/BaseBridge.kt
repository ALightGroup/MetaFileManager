package com.alg.matefile.lib.framework.domain

import com.alg.matefile.lib.framework.domain.manager.ServiceManager

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