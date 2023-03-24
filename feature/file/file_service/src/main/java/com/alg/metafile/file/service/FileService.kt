package com.alg.metafile.file.service

import android.content.Context
import com.alg.matefile.lib.framework.domain.IBridgeCenter
import com.alg.metafile.fileexport.IFileService
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/file/test")
class FileService : IFileService, IBridgeCenter<FileModule> {

  private val fileModule: FileModule by lazy { FileModule() }

  override fun toActivity() {

  }

  override fun init(context: Context?) {
  }

  override fun getBridgeModule(): FileModule = fileModule
}