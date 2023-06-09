package ru.vlasov.mydi.instance

abstract class InstanceFactory<T>(private val definition: () -> T) {

    abstract fun get(): T

    protected fun create(): T {
        try {
            return definition.invoke()
        } catch (e: Exception) {
            throw IllegalArgumentException("Could not create instance")
        }
    }
}