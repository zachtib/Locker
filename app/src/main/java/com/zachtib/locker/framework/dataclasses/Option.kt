package com.zachtib.locker.framework.dataclasses

sealed class Option<out A> {
    companion object {
        fun <A> just(a: A): Option<A> = Some(a)
        fun <A> empty(): Option<A> = None
    }

    abstract fun isEmpty(): Boolean

    object None : Option<Nothing>() {
        override fun isEmpty() = true
    }
    data class Some<out A>(val value: A) : Option<A>() {
        override fun isEmpty() = false
    }

    fun <B> map(f: (A) -> B): Option<B> = when(this) {
        is Some -> Option.Some(f(this.value))
        None -> this
    }
}