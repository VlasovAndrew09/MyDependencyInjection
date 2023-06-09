package ru.vlasov.mydependencyinjection.data.remote

import retrofit2.Call
import retrofit2.http.GET
import ru.vlasov.mydependencyinjection.data.remote.model.FoxDto

interface FoxApi {

    @GET("floof")
    fun getFox(): Call<FoxDto>
}