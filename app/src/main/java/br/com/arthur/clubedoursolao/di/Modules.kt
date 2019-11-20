package br.com.arthur.clubedoursolao.di

import android.content.Context
import br.com.arthur.clubedoursolao.api.Api
import br.com.arthur.clubedoursolao.api.AuthInterceptor
import br.com.arthur.clubedoursolao.api.BaseInterceptor
import br.com.arthur.clubedoursolao.repository.AuthRepository
import br.com.arthur.clubedoursolao.repository.AuthRepositoryImpl
import br.com.arthur.clubedoursolao.util.Constants
import br.com.arthur.clubedoursolao.view.login.LoginViewModel
import br.com.arthur.clubedoursolao.view.splash.SplashViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module{
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
}

val repositoryModule = module{
   single<AuthRepository>{AuthRepositoryImpl(get())}
}

val networkModule = module {
    single<Interceptor>{ BaseInterceptor() }
    //single<Interceptor>{ AuthInterceptor() }
    single{ createOkhttpClientAuth(get())}
    single{ createNetworkClient(get(),get(named("baseURL"))).create(Api::class.java)}
    single(named("baseURL")){Constants.baseURL}
}


private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl : String): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
private fun createOkhttpClientAuth(authInterceptor: Interceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
    return builder.build()
}