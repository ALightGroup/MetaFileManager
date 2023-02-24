package com.alg.metafile.file.service

import com.alg.matefile.lib.framework.module.BaseBridge
import com.alg.matefile.lib.framework.service.IService

class FileBridge : BaseBridge<FileModule>() {

  override fun getServiceClass(): Class<out IService> = FileService::class.java
}