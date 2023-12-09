package com.meng.hooknewapitest.constant

import com.highcapable.yukihookapi.YukiHookAPI.Status.Executor.name
import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.MethodData

val HookMethodTestsTask1Method by lazy {
    System.loadLibrary("dexkit")
    DexKitBridge.create(appApiClassLoader!!,false).use {
        it.findMethod {
            searchPackages("com.meng.newapitestapp")
            matcher {
                declaredClass = "com.meng.newapitestapp.HookMethodTests"
                name = "task1"
            }
        }.first().getMethodInstance(appApiClassLoader!!)
    }
}