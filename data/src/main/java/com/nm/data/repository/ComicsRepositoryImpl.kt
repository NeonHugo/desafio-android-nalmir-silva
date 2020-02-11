package com.nm.data.repository

import com.nm.domain.entity.Comic
import com.nm.domain.repository.ComicsRepository
import com.nm.infrastructure.net.ResultResponse


class ComicsRepositoryImpl(
    private val comicsRemoteDataSource: ComicsRemoteDataSource
) : ComicsRepository {

    override suspend fun showComicsAvailable(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<List<Comic>> {
        return comicsRemoteDataSource.showComicsAvailable(characterId, ts, apiKey, hash)
    }

}