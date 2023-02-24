package com.alg.matefile.lib.framework.repository

import com.alg.matefile.lib.framework.service.LocalService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class LifecycleRepository(private val center: LocalService) : BaseRepository() {

  protected val scope = CoroutineScope(Dispatchers.Default + Job())

  final override fun stateRemove(key: Int) {
    data.remove(key)
    if (data.size == 0) {
      center.releaseRepository(getRepositoryClass())
    }
  }

  abstract fun getRepositoryClass(): Class<out LifecycleRepository>
}

interface LifecycleRepositoryFactory<T : LifecycleRepository> {

  fun create(center: LocalService) : T
}