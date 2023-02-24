package com.alg.metafile.file.service

import android.content.Context
import com.alg.matefile.lib.framework.module.IBridgeCenter
import com.alg.metafile.fileexport.IFileService

class FileService : IFileService, IBridgeCenter<FileModule> {

  private val fileModule: FileModule by lazy { FileModule() }

  override fun toActivity() {

  }

  override fun init(context: Context?) {
  }

  override fun getBridgeModule(): FileModule = fileModule
}