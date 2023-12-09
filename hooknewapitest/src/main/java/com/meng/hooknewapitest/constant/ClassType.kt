package com.meng.hooknewapitest.constant

import com.highcapable.yukihookapi.hook.factory.toClass
import com.highcapable.yukihookapi.hook.factory.toClassOrNull
import com.highcapable.yukihookapi.hook.log.YLog
import com.meng.hooknewapitest.hook.InitHooker.appClassLoader

var appApiClassLoader: ClassLoader? = null

val HookTestsClass by lazy {
    //假设通过N层反射获取
    "com.meng.newapitestapp.HookTests".toClassOrNull(appClassLoader)
}