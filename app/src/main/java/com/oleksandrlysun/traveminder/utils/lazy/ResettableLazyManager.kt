package com.oleksandrlysun.traveminder.utils.lazy

import com.oleksandrlysun.traveminder.utils.Resettable
import java.util.LinkedList

class ResettableLazyManager {
    private val managedDelegates = LinkedList<Resettable>()

    fun register(managed: Resettable) {
        synchronized(managedDelegates) {
            managedDelegates.add(managed)
        }
    }

    fun reset() {
        synchronized(managedDelegates) {
            managedDelegates.forEach { it.reset() }
            managedDelegates.clear()
        }
    }
}