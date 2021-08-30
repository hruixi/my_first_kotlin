package com.huahua.mykotlin;

import com.huahua.mykotlin.MyAnnotation.MyAnnotation;

import java.lang.reflect.Method;

/**
 * ClassName JavaUnitTest
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/6 17:51
 */
public class ExampleUnitTest {
    private static void parseMyAnnotation_class() {
        try {
            Class clazz = Class.forName("com.huahua.mykotlin.MyAnnotationClazz");
            boolean hasAnnotation =  clazz.isAnnotationPresent(MyAnnotation.class);
            if (hasAnnotation) {
                clazz.getAnnotation(MyAnnotation.class);

                MyAnnotation annotation = (MyAnnotation) clazz.getAnnotation(MyAnnotation.class);
                assert annotation != null;
                System.out.println(annotation.type());
            }

        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }

    }

    private static void parseMyAnnotation_method() {
        try {
            Class clazz = Class.forName("com.huahua.mykotlin.MyAnnotationClazz");
            Method method = clazz.getMethod("testMyAnnotation");
            boolean hasAnnotation =  method.isAnnotationPresent(MyAnnotation.class);
            if (hasAnnotation) {
                method.getAnnotation(MyAnnotation.class);

                MyAnnotation myAnnotation = (MyAnnotation) method.getAnnotation(MyAnnotation.class);
                assert myAnnotation != null;
                System.out.println(myAnnotation.method());
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    public static void main(String[] args) {
        parseMyAnnotation_class();
        parseMyAnnotation_method();
    }
}
