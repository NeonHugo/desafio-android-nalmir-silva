package com.nm.domain.repository

import com.nm.domain.entity.Comic
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.ResultResponse

interface ComicsRepository {
    suspend fun showComicsAvailable(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<List<Comic>>
}