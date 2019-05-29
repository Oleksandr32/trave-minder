package com.oleksandrlysun.traveminder.utils

import com.oleksandrlysun.traveminder.utils.lazy.ResettableLazy
import com.oleksandrlysun.traveminder.utils.lazy.ResettableLazyManager

fun <T> resettableLazy(manager: ResettableLazyManager, init: () -> T): ResettableLazy<T> {
    return ResettableLazy(manager, init)
}