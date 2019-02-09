package com.zachtib.locker

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

object CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // Only log Warnings and above to Crashlytics
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }
        Crashlytics.log(priority, tag, message)
        if (t != null) {
            Crashlytics.logException(t)
        }
    }

}