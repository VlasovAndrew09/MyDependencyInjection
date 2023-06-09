package ru.vlasov.mydependencyinjection.domain

sealed class ResultFox<out T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : ResultFox<T>(data)
    class Error<T>(message: String, data: T? = null) : ResultFox<T>(data, message)
}
