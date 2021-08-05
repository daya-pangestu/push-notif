package com.daya.shared.taha.data

sealed class Resource<out T> {
    data class Success<out T>(val data: T): Resource<T>()
    data class Error(val exceptionMessage: String?): Resource<Nothing>()
    data class Loading(var progress : String? = null): Resource<Nothing>()

    companion object {
        fun loading(progress: String? = "") = Loading(progress = progress)
        fun <T> success(data: T) = Success(data)
        fun error(message: String?) = Error(message)
    }
}