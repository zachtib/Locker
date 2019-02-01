package com.zachtib.locker.framework.dataclasses

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Content<T>(val value: T) : Resource<T>()
    data class Error(val throwable: Throwable) : Resource<Nothing>()
}
