package com.example.momoexam.utils

sealed class Result<out T> {
    data object Loading : Result<Nothing>()
    data class Error(val errorMessage: Int) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
}