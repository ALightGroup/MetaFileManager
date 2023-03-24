package com.alg.matefile.lib.framework.data.repository

abstract class RepositoryFlowStream<T, V>(private val downstream: RepositoryFlowCollector<V>) :
  RepositoryFlowCollector<T>() {

  final override suspend fun emit(value: T) {
    downstream.emit(transform(value))
  }

  override fun close() {
    super.close()
    downstream.close()
  }

  protected abstract fun transform(value: T): V
}