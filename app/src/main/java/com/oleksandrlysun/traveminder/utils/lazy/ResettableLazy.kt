package com.oleksandrlysun.traveminder.utils.lazy

import com.oleksandrlysun.traveminder.utils.Resettable
import kotlin.reflect.KProperty

class ResettableLazy<T>(private val manager: ResettableLazyManager, val init: () -> T) : Resettable {

    @Volatile
    var lazyHolder = makeInitBlock()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return lazyHolder.value
    }

    override fun reset() {
        lazyHolder = makeInitBlock()
    }

    private fun makeInitBlock(): Lazy<T> {
        return lazy {
            manager.register(this)
            init()
        }
    }
}