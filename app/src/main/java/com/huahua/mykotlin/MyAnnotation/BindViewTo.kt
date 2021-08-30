package com.huahua.mykotlin.MyAnnotation

/**
 * ClassName BindViewTo
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/11 15:41
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class BindViewTo(val resId:Int = -1)


@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class BindViewByID(val resId:Int = -1)