package com.zachtib.locker.framework.camerakit

import com.camerakit.CameraKitView
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

suspend fun CameraKitView.captureImageAsync() = suspendCoroutine<ByteArray> { continuation ->
    this.errorListener = CameraKitView.ErrorListener { _, exception ->
        this.removeErrorListener()
        continuation.resumeWithException(exception)
    }
    this.captureImage { _, bytes ->
        this.removeErrorListener()
        continuation.resume(bytes)
    }
}