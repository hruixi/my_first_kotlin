package com.huahua.mykotlin;

import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

/**
 * ClassName JavaUnitTest
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/4 16:33
 */

//这个类名就是顶层文件名+“Kt”后缀
public final class ExtendsionTextViewKt {
    //get()方法所对应生成静态函数，并且传入一个接收者类型对象作为参数
    public static final boolean isBolder(@NotNull TextView $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.getPaint().isFakeBoldText();
    }
    //set()方法所对应生成静态函数，并且传入一个接收者类型对象作为参数和一个需要set的参数
    public static final void setBolder(@NotNull TextView $receiver, boolean value) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        $receiver.getPaint().setFakeBoldText(true);
    }
}
