package com.rain.myannotationlib

@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FIELD)
annotation class BindViewByID(val resId:Int = -1)