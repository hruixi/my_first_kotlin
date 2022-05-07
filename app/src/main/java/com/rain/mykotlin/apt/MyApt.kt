package com.rain.mykotlin.apt

import android.app.Activity
import com.rain.mykotlin.MyAnnotation.BindViewTo

/**
 * ClassName MyApt
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/11 15:45
 */
fun Activity.bindAllViewsByAnnotation(activity: Activity) {
    //获取activity的所有属性
    val fields = activity::class.java.fields

    for (field in fields) {
        if (field.isAnnotationPresent(BindViewTo::class.java)) {
            field.isAccessible = true //获取访问权限
            val bindViewTo = field.getAnnotation(BindViewTo::class.java)
            val resId = bindViewTo?.resId?:-1 //获取View id
            if (resId != -1)
                field.set(activity, activity.findViewById(resId))
        }
    }
}