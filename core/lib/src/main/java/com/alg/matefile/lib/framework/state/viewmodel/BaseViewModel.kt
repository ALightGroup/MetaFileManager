package com.alg.matefile.lib.framework.state.viewmodel

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

  @CallSuper
  protected open fun <T> observeData(
    collector: RepositoryFlowCollector<T>,
    action: suspend (RepositoryFlowCollector<T>) -> Unit
  ) {
    viewModelScope.launch {
      action(collector)
    }
  }
}