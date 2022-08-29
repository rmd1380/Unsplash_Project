package com.example.unsplashproject.services

import okhttp3.Interceptor
import okhttp3.Response

class ServiceInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(
                "Authorization",
                "Client-ID xvJiC8gqtS0HJuJrDjQpDe5cWghraEfPnu3Tqh5O81M"
            )
            .build()
        return chain.proceed(request)
    }
}