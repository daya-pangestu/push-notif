package com.daya.taha.util

import android.content.res.Resources

fun dpToPx(dp: Int): Int {
    return ((dp * Resources.getSystem().displayMetrics.density).toInt());
}