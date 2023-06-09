package ru.vlasov.mydependencyinjection.ui

import ru.vlasov.mydependencyinjection.domain.FoxRepository
import ru.vlasov.mydependencyinjection.domain.ResultFox
import ru.vlasov.mydependencyinjection.utils.Observer
import ru.vlasov.mydi.get
import ru.vlasov.mydi.inject

class FoxViewModel {

    private val foxRepository: FoxRepository by inject("remote")
    private val foxArtRepository: FoxRepository by inject("local")

    val showLoading = Observer<Boolean>()
    val urlImageFox = Observer<String?>()
    val textError = Observer<String?>()

    fun getFox() {
        showLoading.post(true)
        textError.post(null)
        foxRepository.getFox { result ->
            showLoading.post(false)
            when(result) {
                is ResultFox.Success -> {
                    result.data?.let { urlImageFox.post(it.urlImageFox) }
                }
                is ResultFox.Error -> {
                    urlImageFox.post(null)
                    result.message?.let { textError.post(it) }
                }
            }
        }
    }

    fun getArtFox() {
        textError.post(null)
        foxArtRepository.getFox {
            urlImageFox.post(it.data!!.urlImageFox)
        }
    }

    fun removeAllObservers() {
        showLoading.removeAllObservers()
        urlImageFox.removeAllObservers()
        textError.removeAllObservers()
    }
}