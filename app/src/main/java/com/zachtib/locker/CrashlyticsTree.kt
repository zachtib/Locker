package com.zachtib.locker

import android.util.Log
import com.crashlytics.android.Crashlytics
import timber.log.Timber

object CrashlyticsTree : Timber.Tree() {

    private val CRASHLYTICS_KEY_PRIORITY = "priority";
    private val CRASHLYTICS_KEY_TAG = "tag";
    private val CRASHLYTICS_KEY_MESSAGE = "message"

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        // Only log Warnings and above to Crashlytics
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }
        Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority)
        Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag)
        Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message)

        Crashlytics.logException(t ?: Exception(message))
    }

}