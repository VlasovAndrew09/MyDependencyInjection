package ru.vlasov.mydependencyinjection.data

import android.app.Application
import android.content.Context
import ru.vlasov.mydependencyinjection.R
import ru.vlasov.mydependencyinjection.domain.FoxRepository
import ru.vlasov.mydependencyinjection.domain.ResultFox
import ru.vlasov.mydependencyinjection.domain.entities.FoxEntity


class FoxArtRepositoryImpl(private val app: Application) : FoxRepository {

    override fun getFox(callback: (ResultFox<FoxEntity>) -> Unit) {
        val resourceId: Int = R.drawable.fox_art
        val uri = "android.resource://${app.packageName}/$resourceId"
        callback(ResultFox.Success(FoxEntity(uri)))
    }
}