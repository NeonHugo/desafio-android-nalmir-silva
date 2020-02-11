package com.nm.desafio_android_nalmir_hugo.di

import com.nm.data.di.MapperModules.mapperModules
import com.nm.desafio_android_nalmir_hugo.di.DataModules.dataModules
import com.nm.desafio_android_nalmir_hugo.di.DataModules.dataSourceModules
import com.nm.desafio_android_nalmir_hugo.di.DataModules.repositoryModules
import com.nm.desafio_android_nalmir_hugo.di.DataModules.serviceModules
import com.nm.desafio_android_nalmir_hugo.di.DataModules.useCaseModules
import org.koin.core.module.Module

object AppComponent {

    fun getAllModules(): List<Module> =
        listOf(*getDataModules())

    private fun getDataModules(): Array<Module> {
        return arrayOf(serviceModules, dataModules, dataSourceModules, repositoryModules, useCaseModules, mapperModules)
    }
}
