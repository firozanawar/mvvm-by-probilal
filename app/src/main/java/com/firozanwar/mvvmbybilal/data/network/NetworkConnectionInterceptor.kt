package com.firozanwar.mvvmbybilal.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.firozanwar.mvvmbybilal.util.NoInternetExceptions
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(context: Context) : Interceptor {

    private val applicationContext = context.applicationContext;

    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isInternetAvailable())
            throw NoInternetExceptions("Make sure you have active internet connectio")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {

        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }
    }
}