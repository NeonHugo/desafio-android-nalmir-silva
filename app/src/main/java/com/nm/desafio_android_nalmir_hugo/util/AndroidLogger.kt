package com.nm.desafio_android_nalmir_hugo.util

import android.content.Context
import android.util.Log
import com.nm.desafio_android_nalmir_hugo.BuildConfig
import com.nm.desafio_android_nalmir_hugo.R
import com.nm.infrastructure.util.toolbox.Logger


class AndroidLogger(context: Context) : Logger {

    private val tag = context.getString(R.string.app_name)

    override fun v(message: String) {
        if (BuildConfig.DEBUG) Log.v(tag, message)
    }

    override fun v(message: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.v(tag, message, tr)
    }

    override fun d(message: String) {
        if (BuildConfig.DEBUG) Log.d(tag, message)
    }

    override fun d(message: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.d(tag, message, tr)
    }

    override fun i(message: String) {
        if (BuildConfig.DEBUG) Log.i(tag, message)
    }

    override fun i(message: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.i(tag, message, tr)
    }

    override fun w(message: String) {
        if (BuildConfig.DEBUG) Log.w(tag, message)
    }

    override fun w(message: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.w(tag, message, tr)
    }

    override fun w(tr: Throwable) {
        if (BuildConfig.DEBUG) Log.w(tag, tr.message, tr)
    }

    override fun e(message: String) {
        if (BuildConfig.DEBUG) Log.e(tag, message)
    }

    override fun e(message: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.e(tag, message, tr)
    }

    override fun e(tr: Throwable) {
        if (BuildConfig.DEBUG)
            Log.e(tag, tr.message, tr)
    }
}