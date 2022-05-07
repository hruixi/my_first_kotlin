package com.rain.mykotlin.MyAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * ClassName MyAnnotation
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/6 10:54
 */
@Target(value = {ElementType.METHOD, ElementType.TYPE,})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String type() default "my annotation:type";
    String method() default "my annotation:method";
}
