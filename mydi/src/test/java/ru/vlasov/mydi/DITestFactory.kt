package ru.vlasov.mydi

import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class DITestFactory {

    class ClassA
    class ClassB(val a: ClassA)

    interface Interface
    class InterfaceImplA : Interface
    class InterfaceImplB : Interface

    @Test
    fun test_factory() {
        startMyDI {
            modules(
                module {
                    factory {
                        ClassA()
                    }
                    factory {
                        ClassB(get())
                    }
                })
        }

        val classA1: ClassA = get()
        val classA2: ClassA = get()
        assertNotNull(classA1)
        assertNotEquals(classA1, classA2)

        val classB: ClassB = get()
        assertNotEquals(classA1, classB.a)
    }

    @Test
    fun test_factory_with_name() {
        startMyDI {
            modules(
                module {
                    factory<Interface>("A") {
                        InterfaceImplA()
                    }
                    factory<Interface>("B") {
                        InterfaceImplB()
                    }
                })
        }

        val interfaceImplA1: Interface = get("A")
        val interfaceImplA2: Interface = get("A")
        assertNotNull(interfaceImplA1)
        assertNotEquals(interfaceImplA1, interfaceImplA2)

        val interfaceImplB: Interface = get("B")
        assertNotNull(interfaceImplB)
        assertNotEquals(interfaceImplA1, interfaceImplB)
    }
}