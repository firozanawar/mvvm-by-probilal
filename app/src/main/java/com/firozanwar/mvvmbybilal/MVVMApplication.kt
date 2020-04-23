package com.firozanwar.mvvmbybilal

import android.app.Application
import com.firozanwar.mvvmbybilal.data.db.AppDatabase
import com.firozanwar.mvvmbybilal.data.network.MyApi
import com.firozanwar.mvvmbybilal.data.network.NetworkConnectionInterceptor
import com.firozanwar.mvvmbybilal.data.repository.QuotesRepository
import com.firozanwar.mvvmbybilal.data.repository.UserRepository
import com.firozanwar.mvvmbybilal.ui.auth.AuthViewModelFactory
import com.firozanwar.mvvmbybilal.ui.auth.ProfileViewModelFactory
import com.firozanwar.mvvmbybilal.ui.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))


        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuotesRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider{ QuotesViewModelFactory(instance())}
    }
}