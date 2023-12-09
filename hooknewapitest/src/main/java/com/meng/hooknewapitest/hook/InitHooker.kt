package com.meng.hooknewapitest.hook

import android.app.Application
import android.content.Context
import com.highcapable.yukihookapi.YukiHookAPI.Status.Executor.name
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.factory.registerModuleAppActivities
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.type.java.StringClass
import com.meng.hooknewapitest.constant.HookMethodTestsTask1Method
import com.meng.hooknewapitest.constant.HookTestsClass
import com.meng.hooknewapitest.hook.跳转新Hooker.hook

object InitHooker : YukiBaseHooker() {
    override fun onHook() {
        Application::class.java!!.method {
            name = "onCreate"
        }.hook().before {
            instance<Context>().registerModuleAppActivities()
        }
        // ————————————————————————————————————————————
        HookTestsClass?.method {
            name = "task1"
            returnType = StringClass
        }?.hook()?.before {
            result = "已经成功被Yuki篡改"
        }
        // 控制篡改按钮一的调用结果
        // ————————————————————————————————————————————

        // ————————————————————————————————————————————
        HookTestsClass?.method {
            name = "task2"
            returnType = StringClass
        }?.hook()?.before {

            HookTestsClass?.method {
                name = "task3"
                returnType = StringClass
            }?.hook()?.before {
                result = "我是被点击task2后才被hook的看到我说明成功篡改"
            }// TODO:失败！！！

            result = "已经通过此次点击去hook了task3，试试效果吧"
        }
        // 控制篡改按钮二的调用结果 并在调用后去尝试篡改按钮三
        // ————————————————————————————————————————————
        loadApp(appName,跳转新Hooker)
        // 尝试在这里启动一个新的Hooker
        // ————————————————————————————————————————————
        HookTestsClass?.method {
            name = "task5"
            returnType = StringClass
        }?.hook()?.before {
            result = "已经成功被Yuki篡改"
        }//TODO:失效
        // 在启动新的Hooker之后再进行与按钮一一样的hook方式看测试结果
        // ————————————————————————————————————————————


    }
}