package com.nm.domain.usecase

import com.nm.domain.entity.Comic
import com.nm.domain.repository.ComicsRepository
import com.nm.infrastructure.net.ApiError
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse


class ComicUseCaseImpl(
    private val mComicsRepository: ComicsRepository
) : ComicUseCase {

    override suspend fun showComic(
        characterId: String,
        ts: String,
        apiKey: String,
        hash: String
    ): ResultResponse<Comic> {
        return when (val results = mComicsRepository.showComicsAvailable(characterId, ts, apiKey, hash)) {
            is SuccessResponse -> {
                SuccessResponse(highiestPrice(results.body))
            }
            else -> {
                ErrorResponse(
                    ApiError(
                        10,
                        "General Error!",
                        "General Error!"
                    )
                )
            }
        }
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