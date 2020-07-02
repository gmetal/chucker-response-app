package com.github.gmetal.chucker

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.github.aurae.retrofit2.LoganSquareConverterFactory
import kotlinx.serialization.UnstableDefault
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

@OptIn(UnstableDefault::class)
class RetrofitClientSingleton private constructor(context: Context) {
    private val retrofit: Retrofit
    val jsonPlaceholderService: JsonPlaceholderService

    companion object : SingletonHolder<RetrofitClientSingleton, Context>(::RetrofitClientSingleton)

    private val baseUrl = "https://jsonplaceholder.typicode.com"

    init {
        val chuckerInterceptor = ChuckerInterceptor(context)
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(
                Interceptor { chain ->
                    chuckerInterceptor.intercept(chain)
                }
            )
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(LoganSquareConverterFactory.create())
            //.addConverterFactory(MoshiConverterFactory.create())
            //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

        jsonPlaceholderService = retrofit.create()
    }
}