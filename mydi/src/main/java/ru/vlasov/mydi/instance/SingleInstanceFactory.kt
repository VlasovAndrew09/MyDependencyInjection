package ru.vlasov.mydi.instance

class SingleInstanceFactory<T>(definition: () -> T) : InstanceFactory<T>(definition) {

    private val value: T by lazy { create() }

    override fun get(): T = value
}