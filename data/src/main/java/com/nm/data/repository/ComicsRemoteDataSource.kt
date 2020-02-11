package com.nm.data.repository

import com.nm.domain.entity.Comic
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.ResultResponse

interface ComicsRemoteDataSource {
    suspend fun showComicsAvailable(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<List<Comic>>
}
