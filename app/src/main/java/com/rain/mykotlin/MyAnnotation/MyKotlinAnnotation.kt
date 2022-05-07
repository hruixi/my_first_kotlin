package com.rain.mykotlin.MyAnnotation

/**
 * ClassName MyKotlinAnnotation
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/6 17:41
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class MyKotlinAnnotation(val clazz:String, val method:String)
