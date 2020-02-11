package com.nm.domain.usecase

import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.ResultResponse

interface MCharacterUseCase {
    suspend fun showCharactersAvailable(
        ts: String,
        apiKey: String,
        hash: String,
        offset: String,
        limit: String
    ): ResultResponse<List<MCharacter>>
}