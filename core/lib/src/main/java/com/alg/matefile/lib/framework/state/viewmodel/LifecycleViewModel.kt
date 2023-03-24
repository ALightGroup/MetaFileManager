package com.alg.matefile.lib.framework.state.viewmodel

import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector

abstract class LifecycleViewModel : BaseViewModel() {

  override fun <T> observeData(
    collector: RepositoryFlowCollector<T>,
    action: suspend (RepositoryFlowCollector<T>) -> Unit
  ) {
    addCloseable(collector)
    super.observeData(collector, action)
  }
}