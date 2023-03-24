package com.alg.matefile.lib.framework.domain

import com.alg.matefile.lib.framework.data.repository.LifecycleRepository
import com.alg.matefile.lib.framework.data.repository.LifecycleRepositoryFactory

abstract class LocalService {

  private val lifecycleRepositories: HashMap<Class<*>, LifecycleRepository> = hashMapOf()

  private val lifecycleRepositoryFactories: HashMap<Class<*>, LifecycleRepositoryFactory<*>> by lazy { getRepositoryFactories() }

  @Suppress("UNCHECKED_CAST")
  protected fun <T : LifecycleRepository> getLifecycleRepository(clazz: Class<T>): T {
    if (lifecycleRepositories[clazz] == null) {
      val factory = lifecycleRepositoryFactories[clazz]
        ?: throw java.lang.RuntimeException("未实现的 Repository 工厂类")
      lifecycleRepositories[clazz] = factory.create(this)
    }
    return lifecycleRepositories[clazz] as T
  }

  internal fun releaseRepository(clazz: Class<out LifecycleRepository>) {
    lifecycleRepositories.remove(clazz)
  }

  abstract fun getRepositoryFactories(): HashMap<Class<*>, LifecycleRepositoryFactory<*>>
}