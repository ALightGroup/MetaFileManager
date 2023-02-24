package com.alg.metafile.file.repository

import com.alg.matefile.lib.framework.repository.GlobalRepository
import com.alg.matefile.lib.framework.repository.RepositoryDataState

class FileRepository : GlobalRepository() {
  override fun getRepositoryData(): HashMap<Int, RepositoryDataState<*>> = hashMapOf()
}