package com.example.paging3jetpackcompose.data.remote

import com.example.paging3jetpackcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Client-ID ${ BuildConfig.API_KEY}")
            .build()

        return chain.proceed(request)
    }
}