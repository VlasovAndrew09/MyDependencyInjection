package ru.vlasov.mydependencyinjection.di

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.vlasov.mydependencyinjection.data.FoxArtRepositoryImpl
import ru.vlasov.mydependencyinjection.data.FoxRepositoryImpl
import ru.vlasov.mydependencyinjection.data.remote.FoxApi
import ru.vlasov.mydependencyinjection.domain.FoxRepository
import ru.vlasov.mydi.get
import ru.vlasov.mydi.module

val retrofitModule = module {
    single("url") {
        "https://randomfox.ca/"
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>("url"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val appModule = module {
    single<FoxApi> {
        get<Retrofit>().create(FoxApi::class.java)
    }
    single<FoxRepository>("remote") {
        FoxRepositoryImpl(get())
    }
    single<FoxRepository>("local") {
        FoxArtRepositoryImpl(get())
    }
}