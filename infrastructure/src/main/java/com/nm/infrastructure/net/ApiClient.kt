package com.nm.infrastructure.net

import android.content.Context
import com.google.gson.Gson
import com.nm.infrastructure.BuildConfig
import com.nm.infrastructure.R
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuild {

    const val NO_AUTHENTICATION = "No-Authentication"
    const val NO_AUTHENTICATION_HEADER = "No-Authentication: true"

    inline fun <reified T> makeService(context: Context): T {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().setLevel(getLogLevel()))
        }.build().let { retrofitCreate(context, it) }
    }

    inline fun <reified T> makeLoggedService(context: Context, interceptor: Interceptor): T {
        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            addInterceptor(HttpLoggingInterceptor().setLevel(getLogLevel()))
        }.build().let { retrofitCreate(context, it) }
    }

    fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    inline fun <reified T> retrofitCreate(context: Context, okHttpClient: OkHttpClient): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
        return retrofit.create(T::class.java)
    }

}