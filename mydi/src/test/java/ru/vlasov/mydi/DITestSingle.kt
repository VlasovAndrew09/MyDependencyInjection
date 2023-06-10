package ru.vlasov.mydi

import android.app.Application
import android.content.Context
import org.junit.Assert.*
import org.junit.Test

class DITestSingle {

    class ClassA
    class ClassB(val a: ClassA)

    interface Interface
    class InterfaceImplA : Interface
    class InterfaceImplB : Interface

    @Test
    fun test_single() {
        startMyDI {
            modules(
                module {
                    single {
                        ClassA()
                    }
                    single {
                        ClassB(get())
                    }
                })
        }

        val classA1: ClassA = get()
        val classA2: ClassA by inject()
        assertNotNull(classA1)
        assertEquals(classA1, classA2)

        val classB: ClassB = get()
        assertEquals(classA1, classB.a)
    }

    @Test
    fun test_single_with_name() {
        startMyDI {
            modules(
                module {
                    single<Interface>("A") {
                        InterfaceImplA()
                    }
                    single<Interface>("B") {
                        InterfaceImplB()
                    }
                })
        }

        val interfaceImplA: Interface = get("A")
        val interfaceImplB: Interface by inject("B")
        assertNotNull(interfaceImplA)
        assertNotNull(interfaceImplB)
        assertNotEquals(interfaceImplA, interfaceImplB)
    }

    @Test
    fun test_android_context() {
        startMyDI {
            modules(
                module {
                    androidContext(Application())
                })
        }

        val application: Application = get()
        assertNotNull(application)
    }
}