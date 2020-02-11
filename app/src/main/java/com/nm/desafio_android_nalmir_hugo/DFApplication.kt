package com.nm.desafio_android_nalmir_hugo

import androidx.multidex.MultiDexApplication
import com.nm.desafio_android_nalmir_hugo.di.AppComponent.getAllModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin

open class DFApplication : MultiDexApplication(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger()
            androidContext(this@DFApplication)
            modules(getAllModules())
        }
    }

}