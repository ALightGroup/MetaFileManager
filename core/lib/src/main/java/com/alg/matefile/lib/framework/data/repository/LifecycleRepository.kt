package com.alg.matefile.lib.framework.data.repository

import com.alg.matefile.lib.framework.domain.LocalService
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

  override suspend fun <T> observeData(type: Int, collector: RepositoryFlowCollector<T>) {
    if (data[type] == null) {
      data[type] = requestRepositoryDataState(type)
    }
    super.observeData(type, collector)
  }

  override fun <T> setData(type: Int, value: T) {
    if (data[type] == null) {
      data[type] = requestRepositoryDataState(type)
    }
    super.setData(type, value)
  }

  override fun <T> getData(type: Int): T {
    if (data[type] == null) {
      data[type] = requestRepositoryDataState(type)
    }
    return super.getData(type)
  }

  override fun getRepositoryData(): HashMap<Int, RepositoryDataState<*>> = hashMapOf()

  abstract fun requestRepositoryDataState(type: Int): RepositoryDataState<*>

  abstract fun getRepositoryClass(): Class<out LifecycleRepository>
}

interface LifecycleRepositoryFactory<T : LifecycleRepository> {

  fun create(center: LocalService): T
}