package com.nm.desafio_android_nalmir_hugo.ui.charactersList

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.OnLifecycleEvent
import com.nm.desafio_android_nalmir_hugo.Constants
import com.nm.domain.entity.MCharacter
import com.nm.domain.usecase.MCharacterUseCase
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.net.SuccessResponse
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData

open class CharactersListViewModel(
    private val characterkUseCase: MCharacterUseCase,
    network: Network
) : BaseViewModel(network) {

    private var offset = 0
    private var page_size = 20

    private val _characters =
        FlexibleLiveData<List<MCharacter>>()

    val characters: LiveData<List<MCharacter>> get() = _characters

    override fun doOnError(throwable: Throwable) {
        _loading.value = false
        _empty.value = true
        _error.value = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCharactersList() {
        launchDataLoad {
            _empty.value = false

            when (val request = characterkUseCase.showCharactersAvailable(
                Constants.TS,
                Constants.APIKEY,
                Constants.HASH,
                offset.toString(),
                page_size.toString()
            )) {
                is SuccessResponse -> {
                    _empty.value = false
                    _characters.value = request.body
                    offset += page_size
                }
                is ErrorResponse -> {
                    _error.value = true
                }
                else -> {
                    _error.value = true
                }
            }
        }
    }

}