package ru.vlasov.mydependencyinjection.utils

interface Observable<T> {
    fun onChange(newValue: T?)
}

class Observer<T>(initialValue: T? = null) {

    private val observers = mutableListOf<Observable<T>>()

    private var value: T? = initialValue
        private set

    fun observe(observer: Observable<T>) {
        observers.add(observer)
        observer.onChange(value)
    }

    private fun notifyObservers() {
        observers.forEach { observer ->
            observer.onChange(value)
        }
    }

    fun removeAllObservers() {
        observers.clear()
    }

    fun post(newValue: T) {
        value = newValue
        notifyObservers()
    }
}

inline fun <T> Observer<T>.observe(crossinline block: (T?) -> Unit) {
    observe(object : Observable<T> {
        override fun onChange(newValue: T?) {
            block(newValue)
        }
    })
}