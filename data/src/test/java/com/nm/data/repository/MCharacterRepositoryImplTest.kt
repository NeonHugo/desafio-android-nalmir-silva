package com.nm.data.repository

import com.nm.domain.entity.MCharacter
import com.nm.domain.repository.MCharacterRepository
import com.nm.infrastructure.net.ApiError
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MCharacterRepositoryImplTest{
    @Mock
    lateinit var charactersRemoteDataSource: CharactersRemoteDataSource

    private lateinit var repository: MCharacterRepository

    @Before
    fun setupTest() {
        MockitoAnnotations.initMocks(this)
        repository = MCharacterRepositoryImpl(charactersRemoteDataSource)
    }

    @Test
    fun successfulComicsListResponse() = runBlocking {
        Mockito.doReturn(
            createCharactersListResult()
        ).`when`(charactersRemoteDataSource).showCharactersAvailable("1", "1", "1", "1", "1")

        val response = repository.showCharactersAvailable("1", "1", "1", "1", "1")

        assertEquals(createCharactersListResult(), response)
    }

    @Test
    fun unsuccessfulComicsListResponse() = runBlocking {
        Mockito.doReturn(
            createUnSuccesfulResponse()
        ).`when`(charactersRemoteDataSource).showCharactersAvailable("1", "1", "1", "1", "1")

        val response = repository.showCharactersAvailable("1", "1", "1", "1", "1")

        assertEquals(
            createUnSuccesfulResponse(), response
        )
    }

    private fun createCharactersListResult(): ResultResponse<List<MCharacter>> {
        val characters = ArrayList<MCharacter>()

        characters.add(
            MCharacter(10L, "Hulk", "Um cara muito paciente", "")
        )

        characters.add(
            MCharacter(20L, "Iron Man", "Um cara muito rico", "")
        )

        return SuccessResponse(characters)
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