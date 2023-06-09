package ru.vlasov.mydi

import ru.vlasov.mydi.instance.FactoryInstanceFactory
import ru.vlasov.mydi.instance.InstanceFactory
import ru.vlasov.mydi.instance.SingleInstanceFactory
import ru.vlasov.mydi.qualifier.Qualifier

class Module {

    val instances = hashMapOf<Qualifier, InstanceFactory<*>>()

    inline fun <reified T> single(
        name: String? = null,
        noinline definition: () -> T
    ) {
        val factory = SingleInstanceFactory(definition)
        instances[Qualifier(T::class, name)] = factory
    }

    inline fun <reified T> factory(
        name: String? = null,
        noinline definition: () -> T
    ) {
        val factory = FactoryInstanceFactory(definition)
        instances[Qualifier(T::class, name)] = factory
    }
}

fun module(moduleDeclaration: Module.() -> Unit): Module = Module().apply(moduleDeclaration)