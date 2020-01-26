package br.com.arthur.clubedoursolao.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import br.com.arthur.clubedoursolao.api.Api
import br.com.arthur.clubedoursolao.api.AuthInterceptor
import br.com.arthur.clubedoursolao.api.BaseInterceptor
import br.com.arthur.clubedoursolao.repository.AuthRepository
import br.com.arthur.clubedoursolao.repository.AuthRepositoryImpl
import br.com.arthur.clubedoursolao.repository.ProductRepository
import br.com.arthur.clubedoursolao.repository.ProductRepositoryImpl
import br.com.arthur.clubedoursolao.util.Constants
import br.com.arthur.clubedoursolao.view.category.CategoryViewModel
import br.com.arthur.clubedoursolao.view.list.DevolutionViewModel
import br.com.arthur.clubedoursolao.view.list.MyProductViewModel
import br.com.arthur.clubedoursolao.view.list.RegisterMyNewProductViewModel
import br.com.arthur.clubedoursolao.view.list.UpdateMyProductViewModel
import br.com.arthur.clubedoursolao.view.login.LoginViewModel
import br.com.arthur.clubedoursolao.view.registerUser.RegisterUserViewModel
import br.com.arthur.clubedoursolao.view.splash.SplashViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val viewModelModule = module {
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterUserViewModel(get()) }
    viewModel { CategoryViewModel(get()) }
    viewModel { MyProductViewModel(get()) }
    viewModel { UpdateMyProductViewModel(get()) }
    viewModel { DevolutionViewModel(get()) }
    viewModel { RegisterMyNewProductViewModel(get()) }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ProductRepository> { ProductRepositoryImpl(get()) }
}

val sharedPreferencesModule = module {
    single {
        provideSettingsPreferences(androidApplication())
    }
}

val networkModule = module {
    //    single<Interceptor>(named("base")){ BaseInterceptor() }
    single<Interceptor>(named("auth")) { AuthInterceptor(get()) }
    single { createOkhttpClientAuth(get(named("auth"))) }
//    factory{
////        createOkhttpClientAuth(get(named("base")))
//    }
    single { createNetworkClient(get(), get(named("baseURL"))).create(Api::class.java) }
    single { createPicassoAuth(get(), get()) }
    single(named("baseURL")) { Constants.baseURL }
}

private fun createPicassoAuth(context: Context, okHttpClient: OkHttpClient): Picasso {
    return Picasso
        .Builder(context)
        .downloader(OkHttp3Downloader(okHttpClient))
        .build()
}

private fun provideSettingsPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences("Token", Context.MODE_PRIVATE)


private fun createNetworkClient(okHttpClient: OkHttpClient, baseUrl: String): Retrofit {
//
//    val gson = GsonBuilder()
//        .setLenient()
//        .create()

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