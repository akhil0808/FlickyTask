package com.task.flicky.domain.core

import androidx.annotation.LayoutRes


interface Binder {
    @get:LayoutRes
    val layoutRes: Int
}