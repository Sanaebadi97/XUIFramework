package com.hvd.xcore

import android.util.Log
import com.hvd.xcore.extention.ifT

fun log(any: Any) {
    XConfig.logEnabled.ifT {
        Log.d(com.hvd.xcore.XConfig.logTag, any.toString())
    }
}