package com.alg.metafile.file.service

import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector
import com.alg.matefile.lib.framework.domain.BaseBridge
import com.alg.matefile.lib.framework.domain.IService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import java.io.File
import javax.inject.Inject

@ActivityRetainedScoped
class FileBridge @Inject constructor(): BaseBridge<FileModule>() {

  fun getFileList(path: String) {
    module?.getFileList(path)
  }

  suspend fun observeFileList(collector: RepositoryFlowCollector<List<File>?>) {
    module?.observeFileList(collector)
  }

  override fun getServiceClass(): Class<out IService> = FileService::class.java
}