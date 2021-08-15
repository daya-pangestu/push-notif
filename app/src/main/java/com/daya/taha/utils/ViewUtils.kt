package com.daya.taha.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

fun dpToPx(dp: Int): Int {
    return ((dp * Resources.getSystem().displayMetrics.density).toInt());
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this,message,duration).show()
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(),message,duration).show()
}

fun <T> diffUtil(isItemTheSame : (T, T) -> Boolean, isContentTheSame : (T, T) -> Boolean): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = isItemTheSame(oldItem,newItem)
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = isContentTheSame(oldItem,newItem)

    }
}

fun glideListener(
    onLoadFailed: ( e: GlideException?,model: Any?,target: Target<Drawable>?,isFirstResource: Boolean) -> Unit,
    onResourceReady: (resource: Drawable?, model: Any?,target: Target<Drawable>?,dataSource: DataSource?,isFirstResource: Boolean) -> Unit) =
    object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onLoadFailed(e,model,target,isFirstResource)
            return true
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onResourceReady(resource,model,target,dataSource,isFirstResource)
            return false
        }
    }