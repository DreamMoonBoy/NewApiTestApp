package com.meng.hooknewapitest.hook

import android.content.Context
import android.content.ContextWrapper
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.type.android.ActivityThreadClass
import com.highcapable.yukihookapi.hook.type.android.ContextImplClass
import com.highcapable.yukihookapi.hook.type.android.LoadedApkClass
import com.highcapable.yukihookapi.hook.type.java.StringClass
import com.meng.hooknewapitest.constant.HookMethodTestsTask1Method
import com.meng.hooknewapitest.constant.HookTestsClass
import com.meng.hooknewapitest.hook.InitHooker.hook

object 跳转新Hooker : YukiBaseHooker() {

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

    override fun onHook() {
        // ————————————————————————————————————————————
        HookTestsClass?.method {
            name = "task4"
            returnType = StringClass
        }?.hook()?.before {
            result = "我经过了InitHooker跳转过来hook"
        }// 成功
        // 控制篡改按钮四的调用结果
        // ————————————————————————————————————————————
        YLog.warn(context.packageName)//使用反射的context需要在onAppLifecycle后
        //直接通过其他方式获取method再hook看结果按钮六
        HookMethodTestsTask1Method.hook().before {
            result = "已经成功被Yuki篡改"
        }// TODO:失效！！！
    }
}