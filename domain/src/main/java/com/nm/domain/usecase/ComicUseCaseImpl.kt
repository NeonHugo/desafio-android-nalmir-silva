package com.nm.domain.usecase

import com.nm.domain.entity.Comic
import com.nm.domain.repository.ComicsRepository
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse
import java.util.ArrayList


class ComicUseCaseImpl(
    private val mComicsRepository: ComicsRepository
) : ComicUseCase {

    override suspend fun showComic(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<Comic> {
        val comicsList = (mComicsRepository.showComicsAvailable(characterId, ts, apiKey, hash)
                as SuccessResponse).body

        return SuccessResponse(highiestPrice(comicsList))
    }

    private fun highiestPrice(comics: List<Comic>): Comic {
        var comic = comics[0]

        comics.forEach {
            if (comic.price < it.price) {
                comic = it
            }
        }

        return comic
    }

}