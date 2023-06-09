package ru.vlasov.mydependencyinjection.data.mapper

import ru.vlasov.mydependencyinjection.data.remote.model.FoxDto
import ru.vlasov.mydependencyinjection.domain.entities.FoxEntity

fun FoxDto.toFoxEntity() = FoxEntity(
    urlImageFox = image
)