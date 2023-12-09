package com.meng.hooknewapitest.hook

import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.factory.registerModuleAppActivities
import com.highcapable.yukihookapi.hook.factory.toClassOrNull
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import com.meng.hooknewapitest.constant.appApiClassLoader

const val appName = "com.meng.newapitestapp"

@InjectYukiHookWithXposed(isUsingResourcesHook = true, isUsingXposedModuleStatus = true)
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "DreamTest"
            isEnable = true
        }
        isDebug = true
        isEnableModuleAppResourcesCache = true
        isEnableDataChannel = true
    }

    override fun onHook() = encase {
        appApiClassLoader = appClassLoader
        onAppLifecycle {
            //始终无法hook任何内容
//            attachBaseContext { baseContext, hasCalledSuper ->
                loadApp(appName,InitHooker)//开始指定装载hooker
//            }
        }
    }
}