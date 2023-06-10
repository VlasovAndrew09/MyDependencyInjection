package ru.vlasov.mydi

import org.junit.Assert
import org.junit.Assert.assertThrows
import org.junit.Test

class DITestException {

    class ClassA

    interface Interface
    class InterfaceImplA : Interface

    @Test
    fun test_exception() {
        startMyDI {
            modules(
                module {
                    single<Interface>("A") {
                        InterfaceImplA()
                    }
                })
        }

        assertThrows(IllegalArgumentException::class.java) {
            get<ClassA>()
        }

        assertThrows(IllegalArgumentException::class.java) {
            get<Interface>()
        }

        assertThrows(IllegalArgumentException::class.java) {
            get<InterfaceImplA>()
        }
    }
}