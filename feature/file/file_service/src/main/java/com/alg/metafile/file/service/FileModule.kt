package com.alg.metafile.file.service

import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector
import com.alg.matefile.lib.framework.domain.IModule
import java.io.File

class FileModule : IModule {

  private val fileLocalService: FileLocalService by lazy { FileLocalService() }

  // ============================== Service(本地服务调用) ============================== //

  fun getFileList(path: String) {
    fileLocalService.getFileList(path)
  }

  suspend fun observeFileList(collector: RepositoryFlowCollector<List<File>?>) {
    fileLocalService.observeFileList(collector)
  }
}