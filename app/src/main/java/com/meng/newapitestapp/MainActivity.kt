package com.meng.newapitestapp

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lin = LinearLayout(this)
        lin.orientation = LinearLayout.VERTICAL
        lin.addView(Button(this).apply {
            text = "这是第一个按钮，点击后变成固定值HookTests.task1，测试首次正常hook"
            setOnClickListener {
                text = HookTests.task1()
            }
        })
        lin.addView(Button(this).apply {
            text = "这是第二个按钮，点击后变成固定值HookTests.task2，用来测试执行onClick后hook按钮3生效情况"
            setOnClickListener {
                text = HookTests.task2()
            }
        })
        lin.addView(Button(this).apply {
            text = "这是第三个按钮，点击后变成固定值HookTests.task3，默认不修改只在按钮二后测试是否修改成功"
            setOnClickListener {
                text = HookTests.task3()
            }
        })
        lin.addView(Button(this).apply {
            text = "这是第四个按钮，点击后变成固定值HookTests.task4，测试跳转到新的loadHooker中是否生效"
            setOnClickListener {
                text = HookTests.task4()
            }
        })
        lin.addView(Button(this).apply {
            text = "这是第五个按钮，点击后变成固定值HookTests.task5，测试已经跳转新的loadHooker后剩余的代码hook此按钮是否生效"
            setOnClickListener {
                text = HookTests.task5()
            }
        })
        lin.addView(Button(this).apply {
            text = "这是第六个按钮，点击后变成固定值HookMethodTests.task1，测试直接hook方法的结果"
            setOnClickListener {
                text = HookMethodTests.task1()
            }
        })
        setContentView(lin)
    }
}