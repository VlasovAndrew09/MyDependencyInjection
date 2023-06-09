package ru.vlasov.mydependencyinjection

import android.app.Application
import ru.vlasov.mydependencyinjection.di.appModule
import ru.vlasov.mydependencyinjection.di.retrofitModule
import ru.vlasov.mydi.module
import ru.vlasov.mydi.startMyDI

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startMyDI {
            modules(module {
                single<Application> { this@App }
            })
            modules(appModule, retrofitModule)
        }
    }
}