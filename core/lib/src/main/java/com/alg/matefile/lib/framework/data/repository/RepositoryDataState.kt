package com.alg.matefile.lib.framework.data.repository

import kotlinx.coroutines.flow.MutableStateFlow

class RepositoryDataState<T>(
  private val data: MutableStateFlow<T>,
  private val key: Int,
  private val host: BaseRepository
) {

  private val observers: ArrayList<RepositoryFlowCollector<T>> = arrayListOf()

  fun remove(collector: RepositoryFlowCollector<T>) {
    observers.remove(collector)
    if (observers.size == 0) {
      host.stateRemove(key)
    }
  }

  fun setData(value: T) {
    data.value = value
  }

  fun getData(): T = data.value

  suspend fun collect(collector: RepositoryFlowCollector<T>) {
    observers.add(collector)
    data.collect(collector)
  }
}