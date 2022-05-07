package com.rain.myaptlib

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeSpec
import javax.lang.model.element.Modifier
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement


/**
 * ClassName BinderClassCreator
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/12 17:39
 */


/**
 * @param packageElement 包元素
 * @param classElement   类元素
 */
class BinderClassCreator(packageElement: PackageElement, classElement: TypeElement) {
    private val ParamName = "rootView"

    private var mTypeElement: TypeElement? = null
    private var mPackageName: String? = null
    private var mBinderClassName: String? = null
    private val mVariableElements: MutableMap<Int, VariableElement> = HashMap()

    init {
        mTypeElement = classElement
        mPackageName = packageElement.qualifiedName.toString()
        mBinderClassName = classElement.simpleName.toString() + "_ViewBinding"
    }

    fun putElement(id: Int, variableElement: VariableElement) {
        mVariableElements[id] = variableElement
    }

    fun generateJavaCode(): TypeSpec? {
        return TypeSpec.classBuilder(mBinderClassName) //public 修饰类
            .addModifiers(Modifier.PUBLIC) //添加类的方法
            .addMethod(generateMethod()) //构建Java类
            .build()
    }

    private fun generateMethod(): MethodSpec? {
        //获取全类名
        val className = ClassName.bestGuess(mTypeElement!!.qualifiedName.toString())
        //构建方法--方法名
        return MethodSpec.methodBuilder("bindView") //public方法
            .addModifiers(Modifier.PUBLIC) //返回void
            .returns(Void.TYPE) //方法传参（参数全类名，参数名）
            .addParameter(className, ParamName) //方法代码
            .addCode(generateMethodCode())
            .build()
    }

    private fun generateMethodCode(): String? {
        val code = StringBuilder()
        for (id in mVariableElements.keys) {
            val variableElement = mVariableElements[id]
            //变量名称
            val name = variableElement!!.simpleName.toString()
            //变量类型
            val type = variableElement.asType().toString()
            //rootView.name = (type)view.findViewById(id), 注意原类中变量声明不能为private，否则这里是获取不到的
            val findViewCode =
                "$ParamName.$name=($type)$ParamName.findViewById($id);\n"
            code.append(findViewCode)
        }
        return code.toString()
    }

    fun getPackageName(): String? {
        return mPackageName
    }
}