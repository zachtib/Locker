package com.zachtib.locker.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class CameraViewModel : ViewModel() {
    private val _message = MutableLiveData<String>()

    interface CameraCallback {
        fun result(bytes: ByteArray)
        fun cancel()
    }

    private val callbacks = mutableListOf<CameraCallback>()

    val message: LiveData<String>
        get() = _message

    fun setMessage(message: String) {
        _message.postValue(message)
    }

    fun setPhotoData(bytes: ByteArray) {
        callbacks.forEach {
            it.result(bytes)
        }
        callbacks.clear()
    }

    suspend fun waitForPhoto() = suspendCancellableCoroutine<ByteArray> { cont ->
        callbacks.add(object : CameraCallback {
            override fun result(bytes: ByteArray) {
                cont.resume(bytes)
            }

            override fun cancel() {
                cont.cancel()
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        callbacks.forEach {
            it.cancel()
        }
        callbacks.clear()
    }
}