package com.alg.metafile.file.repository

import com.alg.matefile.lib.framework.data.repository.GlobalRepository
import com.alg.matefile.lib.framework.data.repository.RepositoryDataState
import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector
import com.blankj.utilcode.util.FileUtils
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File

private const val FILE_LIST_KEY = 1

class FileRepository : GlobalRepository() {

  fun getFileList(path: String) {
    val file = File(path)
    setData(FILE_LIST_KEY, FileUtils.listFilesInDir(file))
  }

  suspend fun observeFileList(collector: RepositoryFlowCollector<List<File>?>) {
    observeData(FILE_LIST_KEY, collector)
  }

  override fun getRepositoryData(): HashMap<Int, RepositoryDataState<*>> {
    val map = hashMapOf<Int, RepositoryDataState<*>>()
    map[FILE_LIST_KEY] =
      RepositoryDataState<List<File>?>(MutableStateFlow(null), FILE_LIST_KEY, this)
    return map
  }
}