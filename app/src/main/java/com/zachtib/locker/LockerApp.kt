package com.zachtib.locker

import android.app.Application
import com.google.firebase.FirebaseApp
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class LockerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        startKoin(this, listOf(appModule))
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        Timber.plant(CrashlyticsTree)
    }
}