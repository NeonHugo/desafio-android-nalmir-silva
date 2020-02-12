package com.nm.domain.usecase

import com.nm.domain.entity.MCharacter
import com.nm.domain.repository.MCharacterRepository
import com.nm.infrastructure.net.ApiError
import com.nm.infrastructure.net.ErrorResponse
import com.nm.infrastructure.net.ResultResponse
import com.nm.infrastructure.net.SuccessResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MCharacterUseCaseImplTest {

    @Mock
    private lateinit var mCharacterRepository: MCharacterRepository

    private lateinit var mCharacterUseCase: MCharacterUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mCharacterUseCase = MCharacterUseCaseImpl(mCharacterRepository)
    }

    @Test
    fun showComicSuccessResponseTest() = runBlocking {
        Mockito.doReturn(
            createCharactersList()
        ).`when`(mCharacterRepository).showCharactersAvailable("1", "1", "1", "1", "1")

        val response = mCharacterUseCase.showCharactersAvailable("1", "1", "1", "1", "1")

        Assert.assertEquals(
            createCharactersList()
            , response
        )
    }

    private fun createCharactersList(): ResultResponse<List<MCharacter>> {
        val characters = ArrayList<MCharacter>()

        characters.add(
            MCharacter(
                10L, "Hulk", "Muito Paciente", ""
            )
        )

        characters.add(
            MCharacter(
                20L, "Iron Man", "Rico", ""
            )
        )


        return SuccessResponse(characters)
    }

    @Test
    fun showComicUnSuccessResponseTest() = runBlocking {
        Mockito.doReturn(
            createUnSuccesfulResponse()
        ).`when`(mCharacterRepository).showCharactersAvailable("1", "1", "1", "1", "1")

        val response = mCharacterUseCase.showCharactersAvailable("1", "1", "1", "1", "1")

        Assert.assertEquals(
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