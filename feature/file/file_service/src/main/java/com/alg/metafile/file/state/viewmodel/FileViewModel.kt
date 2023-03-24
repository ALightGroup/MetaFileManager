package com.alg.metafile.file.state.viewmodel

import androidx.lifecycle.MutableLiveData
import com.alg.matefile.lib.ROOT_PATH
import com.alg.matefile.lib.framework.data.repository.RepositoryFlowCollector
import com.alg.matefile.lib.framework.state.viewmodel.GlobalViewModel
import com.alg.metafile.file.service.FileBridge
import com.alg.metafile.file.state.holder.FileListHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class FileViewModel @Inject constructor(private val bridge: FileBridge) : GlobalViewModel() {

  val files: MutableLiveData<FileListHolder> = MutableLiveData(FileListHolder())

  private val folderStack: ArrayList<String> = arrayListOf()

  init {
    observeData(object : RepositoryFlowCollector<List<File>?>() {
      override suspend fun emit(value: List<File>?) {
        files.value = FileListHolder(value ?: arrayListOf(), folderStack.lastOrNull())
      }
    }) {
      bridge.observeFileList(it)
    }
  }

  fun onBack(): Boolean {
    folderStack.removeLastOrNull()
    return if (folderStack.size != 0) {
      bridge.getFileList(folderStack.last())
      true
    } else {
      false
    }
  }

  fun getFileList(path: String = ROOT_PATH) {
    folderStack.add(path)
    bridge.getFileList(path)
  }
}