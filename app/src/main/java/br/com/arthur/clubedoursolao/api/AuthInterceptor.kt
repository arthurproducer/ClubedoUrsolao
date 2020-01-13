package br.com.arthur.clubedoursolao.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import br.com.arthur.clubedoursolao.LoginActivity
import br.com.arthur.clubedoursolao.model.TokenResponse
import br.com.arthur.clubedoursolao.view.login.LoginFragment
import br.com.arthur.clubedoursolao.view.login.LoginViewModel
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import org.koin.android.viewmodel.ext.android.viewModel

class BaseInterceptor : Interceptor {

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


class AuthInterceptor(private val preferences: SharedPreferences) : Interceptor{

    var credentials = ""
//    var context: Context
    var token : String

//    constructor(context: Context){
//        this.context = context
//        token = getToken(context).toString()
//    }

    init {
        token = preferences.getString("Token",null).toString()
    }

//    fun getToken(context: Context) : String?{
//        val pref = context.getSharedPreferences("Token",0)
//        val token = pref.getString("token",null)
//        return token
//    }

    fun  basicAuthInterceptor(user : String, password: String){
        this.credentials = Credentials.basic(user,password)
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val requestBuilder = chain?.request()?.newBuilder()
        requestBuilder?.addHeader("x-access-token", token)
        val request = requestBuilder?.build()
        val response = chain.proceed(request)
        Log.d("MEUAPP", "Error API KEY TOKEN ${token} + ${response.body().toString()} + ${response.code()}")

        if (response.code() == 401) {
            Log.e("MEUAPP", "Error API KEY TOKEN ${token} + ${response.body().toString()}")
        }
        return response
    }
}
