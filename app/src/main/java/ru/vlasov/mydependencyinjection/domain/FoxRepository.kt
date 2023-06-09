package ru.vlasov.mydependencyinjection.domain

import ru.vlasov.mydependencyinjection.domain.entities.FoxEntity

interface FoxRepository {

    fun getFox(callback: (ResultFox<FoxEntity>) -> Unit)
}