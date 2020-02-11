package com.nm.data.repository

import com.nm.data.model.CharactersResponse
import com.nm.data.services.MarvelService
import com.nm.domain.entity.MCharacter
import com.nm.infrastructure.net.Mapper
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.create

class CharactersRemoteDataSourceImpl(
    private val marvelService: MarvelService,
    private val mapper: Mapper<CharactersResponse, List<MCharacter>>
) : CharactersRemoteDataSource {

    override suspend fun showCharactersAvailable(
        ts: String,
        apiKey: String,
        hash: String,
        offset: String,
        limit: String
    ): ResultResponse<List<MCharacter>> {
        return marvelService.getCharacters(ts, apiKey, hash, offset, limit).create(mapper)
    }

}
