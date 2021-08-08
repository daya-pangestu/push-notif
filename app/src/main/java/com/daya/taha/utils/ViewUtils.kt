package com.daya.taha.utils

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil

fun dpToPx(dp: Int): Int {
    return ((dp * Resources.getSystem().displayMetrics.density).toInt());
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this,message,duration).show()
}

fun <T> diffUtil(isItemTheSame : (T, T) -> Boolean, isContentTheSame : (T, T) -> Boolean): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = isItemTheSame(oldItem,newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = isContentTheSame(oldItem,newItem)

    }
}