package com.alg.metafile.file.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.alg.matefile.lib.framework.ui.BaseActivity
import com.alg.metafile.file.ui.compose.FileUI
import com.alg.metafile.file.state.viewmodel.FileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FileActivity : BaseActivity() {

  private val fileViewModel: FileViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      FileUI(fileViewModel)
    }
    fileViewModel.getFileList()
    onBackPressedDispatcher.addCallback(this) {
      if(!fileViewModel.onBack()){
        finish()
      }
    }
  }
}