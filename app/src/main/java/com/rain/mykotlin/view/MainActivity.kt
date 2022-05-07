package com.rain.mykotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rain.mykotlin.MyAnnotation.BindViewTo
import com.rain.mykotlin.R
import com.rain.mykotlin.apt.bindAllViewsByAnnotation
import com.rain.mykotlin.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    @BindViewTo(R.id.textView)
    lateinit var tv:TextView
    @BindViewTo(R.id.imageView)
    lateinit var iv:ImageView

    /** 创建 ViewModel 方式 1
     通过 kotlin 委托特性创建 ViewModel
     需添加依赖 implementation 'androidx.activity:activity-ktx:1.2.3'
     viewModels() 内部也是通过 创建 ViewModel 方式2 来创建的 ViewModel
    **/
//    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** 用kotlin-android-extensions **/
//        tv = textView
//        iv = imageView
        /** 用反射解析注解 **/
        bindAllViewsByAnnotation(this)
        /** TODO:用apt注解处理器 **/

        /**
         * 创建ViewModel的方式2
         */
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

//        mainViewModel.nameListResult.observe(this, Observer {
//            tv.text = "mainViewModel: nameListResult: $it"
//        })
//        Handler().postDelayed({
//            viewModel.getNames()
//        }, 5000)

        viewModel.displayBean.observe(this, Observer {
            tv.text = "description:${it?.description} \n\n" +
                      "harvest:${it?.harvestGrantNotGrant}"
        })
        Handler(this.mainLooper).postDelayed({
            viewModel.okhttpRequest()
        }, 3000)

//        viewModel.customerInfo()
    }

}