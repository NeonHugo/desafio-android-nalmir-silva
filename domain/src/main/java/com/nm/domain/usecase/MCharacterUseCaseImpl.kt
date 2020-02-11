package com.nm.domain.usecase

import com.nm.domain.entity.MCharacter
import com.nm.domain.repository.MCharacterRepository
import com.nm.infrastructure.net.ResultResponse


class MCharacterUseCaseImpl(
    private val mCharacterRepository: MCharacterRepository
) : MCharacterUseCase {

    override suspend fun showCharactersAvailable(
        ts: String,
        apiKey: String,
        hash: String,
        offset: String,
        limit: String
    ): ResultResponse<List<MCharacter>> {
        return mCharacterRepository.showCharactersAvailable(ts, apiKey, hash, offset, limit)
    }

}