package com.nm.data.repository

import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.ResultResponse

interface CharactersRemoteDataSource {
    suspend fun showCharactersAvailable(
        ts: String,
        apiKey: String,
        hash: String,
        offset: String,
        limit: String
    ): ResultResponse<List<MCharacter>>
}
