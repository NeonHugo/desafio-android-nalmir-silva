package com.nm.data.repository

import com.nm.data.model.ComicsResponse
import com.nm.data.services.MarvelService
import com.nm.domain.entity.Comic
import com.nm.infrastructure.net.Mapper
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.create

class ComicsRemoteDataSourceImpl(
    private val marvelService: MarvelService,
    private val mapper: Mapper<ComicsResponse, List<Comic>>
) : ComicsRemoteDataSource {

    override suspend fun showComicsAvailable(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<List<Comic>> {
        return marvelService.getComics(characterId, ts, apiKey, hash).create(mapper)
    }

}
