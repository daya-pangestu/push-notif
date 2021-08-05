package com.daya.taha.util

import android.content.Context
import android.content.res.Resources
import android.widget.Toast

fun dpToPx(dp: Int): Int {
    return ((dp * Resources.getSystem().displayMetrics.density).toInt());
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this,message,duration).show()
}