package com.vicalmun.elreyestefano

import android.app.Application
import com.vicalmun.elreyestefano.di.baseModule
import com.vicalmun.elreyestefano.di.bookModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ElReyEstefanoApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            // el @ es para cambiar el tipo del contexto al de la app, no a algo de Koin
            androidContext(this@ElReyEstefanoApplication)
            modules(listOf(baseModule, bookModule))
            allowOverride(true)
        }
    }
}