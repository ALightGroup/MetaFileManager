package com.alg.metafile.file.service

import com.alg.matefile.lib.framework.data.repository.LifecycleRepositoryFactory
import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector
import com.alg.matefile.lib.framework.domain.LocalService
import com.alg.metafile.file.repository.FileRepository
import java.io.File

class FileLocalService : LocalService() {

  private val fileRepository: FileRepository by lazy { FileRepository() }

  fun getFileList(path: String) {
    fileRepository.getFileList(path)
  }

  suspend fun observeFileList(collector: RepositoryFlowCollector<List<File>?>) {
    fileRepository.observeFileList(collector)
  }

  override fun getRepositoryFactories(): HashMap<Class<*>, LifecycleRepositoryFactory<*>> =
    hashMapOf()
}