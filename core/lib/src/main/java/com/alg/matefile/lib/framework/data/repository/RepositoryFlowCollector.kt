package com.alg.matefile.lib.framework.data.repository

import androidx.annotation.CallSuper
import kotlinx.coroutines.flow.FlowCollector
import java.io.Closeable

abstract class RepositoryFlowCollector<T> : FlowCollector<T>, Closeable {

  internal var state: RepositoryDataState<T>? = null

  @CallSuper
  override fun close() {
    state?.remove(this)
    state = null
  }
}