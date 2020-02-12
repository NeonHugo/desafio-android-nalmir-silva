package com.nm.domain.usecase

import com.nm.domain.entity.Comic
import com.nm.domain.repository.ComicsRepository
import com.nm.infrastructure.net.ApiError
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations.initMocks

class ComicUseCaseImplTest {

    @Mock
    private lateinit var mComicsRepository: ComicsRepository

    private lateinit var comicUseCase: ComicUseCase

    @Before
    fun setup() {
        initMocks(this)

        comicUseCase = ComicUseCaseImpl(mComicsRepository)
    }

    @Test
    fun showComicSuccessResponseTest() = runBlocking {
        doReturn(
            createComicsList()
        ).`when`(mComicsRepository).showComicsAvailable("1", "1", "1", "1")

        val response = comicUseCase.showComic("1", "1", "1", "1")

        assertEquals(
            SuccessResponse(
                Comic(
                    10L, "Hulk", "O Incrivel Hulk", 22.90, ""
                )
            ), response
        )
    }

    private fun createComicsList(): ResultResponse<List<Comic>> {
        val comics = ArrayList<Comic>()

        comics.add(
            Comic(
                10L, "Hulk", "O Incrivel Hulk", 22.90, ""
            )
        )

        comics.add(
            Comic(
                20L, "Hulk Barato", "O Incrivel Hulk Barato", 10.90, ""
            )
        )

        return SuccessResponse(comics)
    }

    @Test
    fun showComicUnSuccessResponseTest() = runBlocking {
        doReturn(
            createUnSuccesfulResponse()
        ).`when`(mComicsRepository).showComicsAvailable("1", "1", "1", "1")

        val response = comicUseCase.showComic("1", "1", "1", "1")

        assertEquals(
            createUnSuccesfulResponse(), response
        )
    }

    private fun createUnSuccesfulResponse(): ResultResponse<ApiError> {
        return ErrorResponse(
            ApiError(
                10,
                "General Error!",
                "General Error!"
            )
        )
    }
}