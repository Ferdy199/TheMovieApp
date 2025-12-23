package com.ferdsapp.core.utils

sealed class ApiResponse <out T> {
    data class Success<out T>(val data: T): ApiResponse<T>()
    data class Error(val errorMessage: String): ApiResponse<Nothing>()
    data object Loading: ApiResponse<Nothing>()
    data object Empty: ApiResponse<Nothing>()
}