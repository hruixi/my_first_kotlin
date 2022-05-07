package com.rain.mykotlin.model

import com.rain.mykotlin.model.bean.DisplayBean
import com.squareup.okhttp.ResponseBody
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * ClassName MainService
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/18 16:33
 */
interface MainService {
//    @GET("loanapp/display")
//    fun display() : Observable<DisplayBean>
    @GET("loanapp/display")
    fun display() : retrofit2.Response<DisplayBean>

    @GET("record/customer-info")
//    fun customerInfo() : Observable<ResponseBody>
    fun customerInfo(@Header("X-AUTH-TOKEN") token:String) : Observable<ResponseBody>
}