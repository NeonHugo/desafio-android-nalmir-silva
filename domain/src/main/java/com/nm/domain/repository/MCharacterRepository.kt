package com.nm.domain.repository

import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.ResultResponse

interface MCharacterRepository {
    suspend fun showCharactersAvailable(
        ts: String,
        apiKey: String,
        hash: String,
        offset: String,
        limit: String
    ): ResultResponse<List<MCharacter>>
}