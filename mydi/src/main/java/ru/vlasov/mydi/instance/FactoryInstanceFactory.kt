package ru.vlasov.mydi.instance

class FactoryInstanceFactory<T>(definition: () -> T) : InstanceFactory<T>(definition) {

    override fun get(): T = create()
}