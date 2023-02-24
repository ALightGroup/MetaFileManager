package com.alg.metafile.file.service

import com.alg.matefile.lib.framework.repository.LifecycleRepositoryFactory
import com.alg.matefile.lib.framework.service.LocalService

class FileLocalService : LocalService() {

  override fun getRepositoryFactories(): HashMap<Class<*>, LifecycleRepositoryFactory<*>> =
    hashMapOf()
}