package com.alg.matefile.lib.framework.repository

import kotlinx.coroutines.MainScope

abstract class GlobalRepository : BaseRepository() {

  protected val scope = MainScope()

  final override fun stateRemove(key: Int) {
  }
}