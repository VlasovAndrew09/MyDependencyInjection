package ru.vlasov.mydi

import ru.vlasov.mydi.instance.InstanceFactory
import ru.vlasov.mydi.qualifier.Qualifier

class MyDI {

    val instances = HashMap<Qualifier, InstanceFactory<*>>()

    fun loadModules(modules: List<Module>) {
        modules.forEach { module ->
            loadModule(module)
        }
    }

    private fun loadModule(module: Module) {
        module.instances.forEach { (mapping, factory) ->
            instances[mapping] = factory
        }
    }
}