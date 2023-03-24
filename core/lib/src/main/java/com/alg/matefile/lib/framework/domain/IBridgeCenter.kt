package com.alg.matefile.lib.framework.domain

interface IBridgeCenter<T : IModule> {

    fun getBridgeModule(): T

}