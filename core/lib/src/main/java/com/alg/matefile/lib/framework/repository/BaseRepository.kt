package com.alg.matefile.lib.framework.repository

abstract class BaseRepository {

  protected val data: HashMap<Int, RepositoryDataState<*>> by lazy { getRepositoryData() }

  @Suppress("UNCHECKED_CAST")
  suspend fun <T> observeData(type: Int, collector: RepositoryFlowCollector<T>) {
    data[type]?.let {
      val state = it as RepositoryDataState<T>
      collector.state = state
      state.collect(collector)
    }
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> setData(type: Int, value: T) {
    data[type]?.let {
      val state = it as RepositoryDataState<T>
      state.setData(value)
    }
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> getData(type: Int): T = data[type]?.getData() as T

  internal abstract fun stateRemove(key: Int)

  protected abstract fun getRepositoryData(): HashMap<Int, RepositoryDataState<*>>
}