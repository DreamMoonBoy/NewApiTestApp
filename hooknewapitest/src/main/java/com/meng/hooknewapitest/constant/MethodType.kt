package com.meng.hooknewapitest.constant

import android.content.Context
import android.content.ContextWrapper
import com.getkeepsafe.relinker.ReLinker
import com.highcapable.yukihookapi.YukiHookAPI.Status.Executor.name
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.type.android.ActivityThreadClass
import com.highcapable.yukihookapi.hook.type.android.ContextImplClass
import com.highcapable.yukihookapi.hook.type.android.LoadedApkClass
import org.luckypray.dexkit.DexKitBridge
import org.luckypray.dexkit.result.MethodData

val context: Context
    get() {
        val mainThread = ActivityThreadClass.method {
            name = "currentActivityThread"
            emptyParam()
        }.get().call() ?: throw NullPointerException("mainThread 反射值空")
        val mBoundApplication = ActivityThreadClass.field {
            name = "mBoundApplication"
        }.get(mainThread).any() ?: throw NullPointerException("mBoundApplication 反射值空")
        val packageInfo = mBoundApplication.javaClass.field {
            name = "info"
        }.get(mBoundApplication).any() ?: throw NullPointerException("packageInfo 反射值空")
        val contextImpl = ContextImplClass.method {
            name = "createAppContext"
            param(ActivityThreadClass, LoadedApkClass)
        }.get().invoke<Context>(mainThread,packageInfo)
        return ContextWrapper(contextImpl)
    }


val HookMethodTestsTask1Method by lazy {
    //使用反射的context需要在onAppLifecycle后 否则程序将从此刻停止执行
    ReLinker.loadLibrary(context,"dexkit")
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