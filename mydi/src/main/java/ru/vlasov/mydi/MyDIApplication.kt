package ru.vlasov.mydi

import ru.vlasov.mydi.qualifier.Qualifier

object MyDIApplication {

    private val di = MyDI()

    fun modules(modules: Module): MyDIApplication {
        return modules(listOf(modules))
    }

    fun modules(vararg modules: Module): MyDIApplication {
        return modules(modules.toList())
    }

    private fun modules(modules: List<Module>): MyDIApplication {
        loadModules(modules)
        return this
    }

    private fun loadModules(modules: List<Module>) {
        di.loadModules(modules)
    }

    fun <T : Any> get(qualifier: Qualifier): T {
        val instance = di.instances[qualifier]
        if (instance != null) {
            return instance.get() as T
        } else {
            throw IllegalArgumentException("Could not get instance")
        }
    }
}

inline fun startMyDI(declaration: MyDIApplication.() -> Unit): MyDIApplication =
    MyDIApplication.apply(declaration)

inline fun <reified T : Any> get(name: String? = null): T {
    return MyDIApplication.get(Qualifier(T::class, name))
}

inline fun <reified T : Any> inject(name: String? = null): Lazy<T> = lazy {
    get(name)
}