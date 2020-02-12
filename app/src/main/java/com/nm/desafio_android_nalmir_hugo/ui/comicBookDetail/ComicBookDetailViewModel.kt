package com.nm.desafio_android_nalmir_hugo.ui.comicBookDetail

import androidx.lifecycle.LiveData
import com.nm.desafio_android_nalmir_hugo.Constants
import com.nm.domain.entity.Comic
import com.nm.domain.usecase.ComicUseCase
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.SuccessResponse
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData

open class ComicBookDetailViewModel(
    private val comicUseCase: ComicUseCase,
    network: Network
) : BaseViewModel(network) {

    private val _comic =
        FlexibleLiveData<Comic>()

    val comic: LiveData<Comic> get() = _comic

    override fun doOnError(throwable: Throwable) {
        _loading.value = false
        _empty.value = true
        _error.value = true
    }

    fun onComicDetail(characterId: Long) {
        launchDataLoad {
            _empty.value = false

            when (val request = comicUseCase.showComic(
                characterId.toString(),
                Constants.TS,
                Constants.APIKEY,
                Constants.HASH
            )) {
                is SuccessResponse -> {
                    _empty.value = false
                    _comic.value = request.body
                }
                is ErrorResponse -> {
                    _empty.value = true
                    _error.value = true
                }
                else -> {
                    _empty.value = true
                    _error.value = true
                }
            }
        }
    }

}