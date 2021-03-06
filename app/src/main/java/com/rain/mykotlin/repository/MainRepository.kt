package com.rain.mykotlin.repository

import com.rain.mykotlin.model.MainService
import com.rain.mykotlin.model.bean.DisplayBean
import com.squareup.okhttp.ResponseBody
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * ClassName MainRepository
 * Description TODO
 * Author He ruixiang
 * Date 2021/8/16 14:10
 */
class MainRepository {
    private var service: MainService

    init {
        val TIMEOUT = 150
        val okhttpClientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okhttpClientBuilder.connectionPool(ConnectionPool(5, 1, TimeUnit.SECONDS))
//            .addInterceptor(HeaderInterceptor())
            .addInterceptor(loggingInterceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://xfdksjjt.firstcashcash.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClientBuilder.build())
            .build()

        service = retrofit.create(MainService::class.java)
    }

    suspend fun getNameList() : List<String> {
        return withContext(Dispatchers.IO) {
            listOf("张三", "李四")
        }
    }

//    fun okhttpRequest() : Observable<DisplayBean> = service.display()
    suspend fun okhttpRequest() : retrofit2.Response<DisplayBean> {
        return withContext(Dispatchers.IO) {
            service.display()
        }
    }


    fun customerInfo() : Observable<ResponseBody> = service.customerInfo("eyJsb2NhbGUiOiJlbl9VUyIsImFsZyI6IkhTNTEyIn0.eyJzdWIiOiIwOTk1MDc1NDgwMSIsImV4cCI6MTYyOTkxNjM4M30.JF7TGGgVb4DnQnrKM_tulyrCZMVJ9TRogdepLm0Ij--cshy1Eq48jyFx52iIyvUIA3X5PCBXjOuv3fRcSbvHSQ")
//    fun customerInfo() : Observable<ResponseBody> = service.customerInfo()
}

