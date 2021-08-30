package com.huahua.myannotationlib

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class BindViewByID(val resId:Int = -1)