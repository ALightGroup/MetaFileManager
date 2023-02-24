package com.alg.matefile.lib.framework.module

interface IBridgeCenter<T : IModule> {

    fun getBridgeModule(): T

}