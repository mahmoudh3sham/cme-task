package com.cme.task.utils

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorManager {

    fun getCode (throwable: Throwable?) : Int{
        return when (throwable) {
            is SocketTimeoutException -> {
                408 //timeout (slow connection)
            }
            is UnknownHostException -> {
                502 //Bad Gateway (No Internet)
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