package br.com.arthur.clubedoursolao.api

import android.util.Log
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BaseInterceptor : Interceptor {

    var credentials = ""

    fun  basicAuthInterceptor(user : String, password: String){
        this.credentials = Credentials.basic(user,password)
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain?.request()?.newBuilder()
        requestBuilder?.addHeader("Content-Type", "application/json")
        val request = requestBuilder?.build()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY")
        }
        return response
    }}


class AuthInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain?.request()?.newBuilder()
        requestBuilder?.addHeader("Content-Type", "application/json")
        val request = requestBuilder?.build()
        val response = chain.proceed(request)
        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY")
        }
        return response
    }}