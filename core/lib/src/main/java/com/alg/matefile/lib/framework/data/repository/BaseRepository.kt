package com.alg.matefile.lib.framework.data.repository

import androidx.annotation.CallSuper

abstract class BaseRepository {

  internal val data: HashMap<Int, RepositoryDataState<*>> by lazy { getRepositoryData() }

  @Suppress("UNCHECKED_CAST")
  @CallSuper
  protected open suspend fun <T> observeData(type: Int, collector: RepositoryFlowCollector<T>) {
    data[type]?.let {
      val state = it as RepositoryDataState<T>
      collector.state = state
      state.collect(collector)
    }
  }

  @Suppress("UNCHECKED_CAST")
  @CallSuper
  protected open fun <T> setData(type: Int, value: T) {
    data[type]?.let {
      val state = it as RepositoryDataState<T>
      state.setData(value)
    }
  }

  internal abstract fun stateRemove(key: Int)

  @Suppress("UNCHECKED_CAST")
  @CallSuper
  protected open fun <T> getData(type: Int): T = data[type]?.getData() as T

  protected abstract fun getRepositoryData(): HashMap<Int, RepositoryDataState<*>>
}