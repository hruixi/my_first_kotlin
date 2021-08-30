package com.huahua.mykotlin;

import com.huahua.mykotlin.MyAnnotation.MyAnnotation;

import static java.sql.DriverManager.println;


/**
 * ClassName MyAnnotationClazz
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/6 17:53
 */
@MyAnnotation(type = "type: 类的注解")
class MyAnnotationClazz {

    @MyAnnotation( method = "method:方法的注解")
    public void testMyAnnotation() {
        println("执行自定义注解函数");
    }
}
