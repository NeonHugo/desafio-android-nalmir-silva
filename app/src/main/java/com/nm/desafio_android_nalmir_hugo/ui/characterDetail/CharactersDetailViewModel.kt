package com.nm.desafio_android_nalmir_hugo.ui.characterDetail

import androidx.lifecycle.LiveData
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.base.BaseViewModel
import com.nm.infrastructure.net.Network
import com.nm.infrastructure.util.extensions.livedata.FlexibleLiveData

open class CharactersDetailViewModel(
    network: Network
) : BaseViewModel(network) {

    private val _character =
        FlexibleLiveData<MCharacter>()

    val character: LiveData<MCharacter> get() = _character

    override fun doOnError(throwable: Throwable) {
        _loading.value = false
        _empty.value = true
    }

    fun onCharacterDetail(character: MCharacter) {
        launchDataLoad {
            _empty.value = false

            _character.value = character
        }
    }

}