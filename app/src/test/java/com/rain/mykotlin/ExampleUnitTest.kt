package com.rain.mykotlin

import com.rain.mykotlin.MyAnnotation.MyKotlinAnnotation

@MyKotlinAnnotation("kotlin: 我是类的注解", "")
class MyKotlinAnnotationClazz {

    @MyKotlinAnnotation("", "kotlin: 我是方法注解")
    fun methodTest() = println()
}

fun parseMyAnnotation_class() {
    val clazz = Class.forName("com.huahua.mykotlin.MyKotlinAnnotationClazz")
    val hasAnnotation =  clazz.isAnnotationPresent(MyKotlinAnnotation::class.java)
    if (hasAnnotation) {
        val myKotlinAnnotation:MyKotlinAnnotation? = clazz.getAnnotation(MyKotlinAnnotation::class.java)
        println(myKotlinAnnotation?.clazz)
    }

}

fun parseMyAnnotation_method() {
    val clazz = Class.forName("com.huahua.mykotlin.MyKotlinAnnotationClazz")
    val method = clazz.getMethod("methodTest")
    val hasAnnotation =  method.isAnnotationPresent(MyKotlinAnnotation::class.java)
    if (hasAnnotation) {
        val myKotlinAnnotation = method.getAnnotation(MyKotlinAnnotation::class.java)
        println(myKotlinAnnotation?.method)
    }
}




fun main(args: Array<String>) {
//    parseMyAnnotation_class()
//    parseMyAnnotation_method()
    val pps = System.getProperties()
    pps.list(System.out)
}


