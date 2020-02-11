package com.nm.domain.usecase

import com.nm.domain.entity.Comic
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.ResultResponse

interface ComicUseCase {
    suspend fun showComic(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<Comic>
}