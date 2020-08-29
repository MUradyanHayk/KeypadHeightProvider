package com.example.keypadapp.utils

import android.content.res.Resources

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.scaledDensity).toInt()

val Float.dp: Float
    get() = this * Resources.getSystem().displayMetrics.scaledDensity
