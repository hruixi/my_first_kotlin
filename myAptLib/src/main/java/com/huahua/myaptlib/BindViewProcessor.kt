package com.huahua.myaptlib

import com.google.auto.service.AutoService
import com.huahua.myannotationlib.BindViewByID
import com.squareup.javapoet.JavaFile
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.lang.model.util.Elements





@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.huahua.myannotationlib.BindViewTo")
@AutoService(Processor::class)
class BindViewProcessor : AbstractProcessor()  {
    private var mElements: Elements? = null
    private val mCreatorMap: HashMap<String, BinderClassCreator> = HashMap()

    /**
     * 编译期回调方法,apt核心实现方法
     * 针对所有使用目标注解的元素(Element)
     */
    override fun process(set: MutableSet<out TypeElement>?, roundEnvironment: RoundEnvironment): Boolean {
        // 扫描整个工程, 找出所有使用BindViewTo注解的元素
        val elements = roundEnvironment.getElementsAnnotatedWith(BindViewByID::class.java)

        //遍历元素, 为每一个类元素创建一个Creator
        for (element in elements) {
            // BindViewTo限定了只能属性使用, 这里强转为变量元素(VariableElement)
            val variableElement = element as VariableElement
            // 获取封装属性元素的类元素TypeElement
            val classElement = variableElement.enclosingElement as TypeElement
            // 获取简单类名
            val fullClassName = classElement.qualifiedName.toString()
            val creator = mCreatorMap[fullClassName]
            // 如果不存在，则创建一个对应的Creator
            if (creator == null) {
                val creator = BinderClassCreator(mElements!!.getPackageOf(classElement), classElement)
                mCreatorMap[fullClassName] = creator
            }
        }

        //每一个类将生成一个新的java文件，其中包含绑定代码
        for (key in mCreatorMap.keys) {
            val binderClassCreator = mCreatorMap[key]
            //通过javapoet构建生成Java类文件
            val javaFile = JavaFile.builder(binderClassCreator?.getPackageName(), binderClassCreator?.generateJavaCode())
                .build()

            javaFile.writeTo(processingEnv.filer)
        }

        return false
    }

    /**
     * init方法一般用于初始化一些用到的工具类，主要有
     * processingEnvironment.getElementUtils(); 处理Element的工具类，用于获取程序的元素，例如包、类、方法。
     * processingEnvironment.getTypeUtils(); 处理TypeMirror的工具类，用于取类信息
     * processingEnvironment.getFiler(); 文件工具
     * processingEnvironment.getMessager(); 错误处理工具
     */
    @Synchronized
    override fun init(processingEnvironment: ProcessingEnvironment?) {
        super.init(processingEnvironment)
        mElements = processingEnv.elementUtils
    }

    /**
     * 获取Java版本，一般用最新版本
     * 也可以使用注解方式：@SupportedSourceVersion(SourceVersion.RELEASE_7)
     */
//    override fun getSupportedSourceVersion(): SourceVersion? {
//        return SourceVersion.latestSupported()
//    }

    /**
     * 获取目标注解列表
     * 也可以使用注解方式：@SupportedAnnotationTypes("com.xibeixue.apt_annotation.BindViewTo")
     */
//    override fun getSupportedAnnotationTypes(): Set<String> {
//        val supportTypes: HashSet<String> = LinkedHashSet()
//        supportTypes.add(BindViewByID::class.java.canonicalName)
//        return supportTypes
//    }
}