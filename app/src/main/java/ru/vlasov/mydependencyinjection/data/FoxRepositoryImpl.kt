package ru.vlasov.mydependencyinjection.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.vlasov.mydependencyinjection.data.mapper.toFoxEntity
import ru.vlasov.mydependencyinjection.data.remote.FoxApi
import ru.vlasov.mydependencyinjection.data.remote.model.FoxDto
import ru.vlasov.mydependencyinjection.domain.FoxRepository
import ru.vlasov.mydependencyinjection.domain.ResultFox
import ru.vlasov.mydependencyinjection.domain.entities.FoxEntity

class FoxRepositoryImpl(
    private val api: FoxApi
) : FoxRepository {

    override fun getFox(callback: (ResultFox<FoxEntity>) -> Unit) {
        api.getFox().enqueue(object : Callback<FoxDto> {
            override fun onResponse(call: Call<FoxDto>, response: Response<FoxDto>) {
                if (response.isSuccessful) {
                    callback(ResultFox.Success(response.body()!!.toFoxEntity()))
                } else {
                    callback(ResultFox.Error("Ошибка загрузки"))
                }
            }

            override fun onFailure(call: Call<FoxDto>, t: Throwable) {
                callback(ResultFox.Error("Ошибка загрузки"))
            }
        })
    }
}