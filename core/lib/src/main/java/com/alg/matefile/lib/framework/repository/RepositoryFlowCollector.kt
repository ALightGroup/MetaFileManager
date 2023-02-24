package com.alg.matefile.lib.framework.repository

import kotlinx.coroutines.flow.FlowCollector

abstract class RepositoryFlowCollector<T> : FlowCollector<T> {

  internal var state: RepositoryDataState<T>? = null

  private var downstream: RepositoryFlowCollector<*>? = null

  suspend fun <E> map(downstream: RepositoryFlowCollector<E>, value: T) {
    if (this.downstream != null) {
      // TODO 日志报告
      return
    }
    this.downstream = downstream
    emit(value)
  }

  fun onRelease() {
    state?.remove(this)
    state = null
    downstream?.onRelease()
  }
}