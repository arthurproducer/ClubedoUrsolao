package br.com.arthur.clubedoursolao

import android.app.Application
import br.com.arthur.clubedoursolao.di.networkModule
import br.com.arthur.clubedoursolao.di.repositoryModule
import br.com.arthur.clubedoursolao.di.viewModelModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start stetho
        Stetho.initializeWithDefaults(this)
// Start Koin
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    viewModelModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}