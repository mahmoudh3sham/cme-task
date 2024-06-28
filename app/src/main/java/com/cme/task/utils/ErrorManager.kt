package com.cme.task.utils

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ErrorManager {

    fun getCode (throwable: Throwable?) : Int{
        return when (throwable) {
            is SocketTimeoutException -> {
                0
            }
            is IOException -> {
                1
            }
            is HttpException -> {
                throwable.code()
            }
            else -> {
                0
            }
        }
    }
}