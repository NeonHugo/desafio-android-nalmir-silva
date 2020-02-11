package com.nm.data.repository

import com.nm.domain.entity.MCharacter
import com.nm.domain.repository.MCharacterRepository
import com.nm.infrastructure.net.ResultResponse


class MCharacterRepositoryImpl(
    private val charactersRemoteDataSource: CharactersRemoteDataSource
) : MCharacterRepository {


    override suspend fun showCharactersAvailable(
        ts: String,
        apiKey: String,
        hash: String,
        offset: String,
        limit: String
    ): ResultResponse<List<MCharacter>> {
        return charactersRemoteDataSource.showCharactersAvailable(ts, apiKey, hash, offset, limit)
    }

}