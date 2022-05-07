package com.rain.mykotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rain.mykotlin.model.bean.DisplayBean
import com.rain.mykotlin.repository.MainRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * ClassName MainViewModel
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/16 14:32
 */
class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()


    private val nameList = MutableLiveData<List<String>>()
    val nameListResult:MutableLiveData<List<String>> = nameList

    val displayBean = MutableLiveData<DisplayBean>()
//    val okHttpResult:MutableLiveData<DisplayBean> = displayBean

    fun getNames() {
        viewModelScope.launch {
            nameList.value = mainRepository.getNameList()
        }
    }

    fun okhttpRequest() {
        /** 使用rxJava做线程调度 **/
//        mainRepository.okhttpRequest()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                displayBean.value = it
//            },{
//                it.printStackTrace()
//            })
//            .isDisposed

        /** 使用协程进行通信和线程调度 **/
        viewModelScope.launch {
            val response = mainRepository.okhttpRequest()
            Log.i("HRX", "$response")
        }
    }

    fun customerInfo() {
        mainRepository.customerInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                Log.i("HRX", "customerInfo: ${it.string()}")
            }, {

            })
            .isDisposed
    }
}