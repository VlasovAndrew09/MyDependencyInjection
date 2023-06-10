# MyDependencyInjection

Собственная реализация DI фреймворка по аналогии с [Koin](https://github.com/InsertKoinIO/koin)

## Example

```kotlin
class ClassA
class ClassB(val a: ClassA)

interface Interface
class InterfaceImplA : Interface
class InterfaceImplB(val a: ClassA, val b: ClassB) : Interface

val module1 = module {
    single { ClassA() }
    factory { ClassB(get()) }
}

val module2 = module {
    single<Interface>("A") { InterfaceImplA() }
    single<Interface>("B") { InterfaceImplB(get(), get()) }
}

fun main() {
    startMyDI {
        modules(module1, module2)
    }
    
    val classA: ClassA = get()
    val classB: ClassB by inject()
    
    val interfaceImplA: Interface = get("A")
    val interfaceImplB: Interface by inject("B")
}
```
