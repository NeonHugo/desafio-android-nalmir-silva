package com.nm.data.repository

import com.nm.domain.entity.Comic
import com.nm.domain.repository.ComicsRepository
import com.nm.infrastructure.net.ApiError
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations

class ComicsRepositoryImplTest {

    @Mock
    lateinit var comicsRemoteDataSource: ComicsRemoteDataSource

    private lateinit var repository: ComicsRepository

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        repository = ComicsRepositoryImpl(comicsRemoteDataSource)
    }

    @Test
    fun successfulComicsListResponse() = runBlocking {
        doReturn(
            createComicsListResult()
        ).`when`(comicsRemoteDataSource).showComicsAvailable("1", "1", "1", "1")

        val response = repository.showComicsAvailable("1", "1", "1", "1")

        assertEquals(createComicsListResult(), response)
    }

    @Test
    fun unsuccessfulComicsListResponse() = runBlocking {
        doReturn(
            createUnSuccesfulResponse()
        ).`when`(comicsRemoteDataSource).showComicsAvailable("1", "1", "1", "1")

        val response = repository.showComicsAvailable("1", "1", "1", "1")


        assertEquals(
            createUnSuccesfulResponse(), response
        )
    }

    private fun createComicsListResult(): ResultResponse<List<Comic>> {
        val comics = ArrayList<Comic>()

        comics.add(
            Comic(10L, "Incrivel Hulk", "Revista Comemorativa", 2.80, "")
        )

        comics.add(
            Comic(20L, "Incrivel Hulk Amzing", "Revista Comemorativa Amazing", 5.90, "")
        )

        return SuccessResponse(comics)
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