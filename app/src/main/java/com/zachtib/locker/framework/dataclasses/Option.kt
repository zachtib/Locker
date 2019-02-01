package com.zachtib.locker.framework.dataclasses

sealed class Option<out A> {
    object None : Option<Nothing>()
    data class Some<A>(val value: A) : Option<A>()

    fun <B> map(f: (A) -> B): Option<B> = when(this) {
        is Some -> Option.Some(f(this.value))
        None -> None
    }
}