package com.nm.desafio_android_nalmir_hugo.ui.charactersList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nm.domain.entity.MCharacter
import com.nm.domain.usecase.MCharacterUseCase
import com.nm.infrastructure.net.*
import com.nm.infrastructure.util.extensions.livedata.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class CharactersListViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @MockK
    lateinit var mCharacterUseCase: MCharacterUseCase

    @MockK
    lateinit var network: Network

    lateinit var charactersListViewModel: CharactersListViewModel

    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)

        MockKAnnotations.init(this)

        startKoin {
        }

        charactersListViewModel = CharactersListViewModel(mCharacterUseCase, network)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        stopKoin()
    }

    @Test
    fun successfulCharacterList() {
        every {
            runBlocking {
                mCharacterUseCase.showCharactersAvailable(
                    any(), any(), any(), any(), any()
                )
            }
        } returns createCharacterListResult()

        charactersListViewModel.onCharactersList()

        val characters = charactersListViewModel.characters.getOrAwaitValue()
        assertEquals(createCharacterList(), characters)
    }

    private fun createCharacterListResult(): ResultResponse<List<MCharacter>> {
        return SuccessResponse(createCharacterList())
    }

    private fun createCharacterList(): List<MCharacter> {
        val characters = ArrayList<MCharacter> ()

        characters.add(
            MCharacter(10L, "Hulk", "Paciencia", "")
        )

        characters.add(
            MCharacter(20L, "Hulk2", "Paciencia2", "")
        )

        return characters
    }

    @Test
    fun unSuccessfulCharacterList() {
        every {
            runBlocking {
                mCharacterUseCase.showCharactersAvailable(
                    any(), any(), any(), any(), any()
                )
            }
        } returns createUnSuccessfulResult()

        charactersListViewModel.onCharactersList()

        val error = charactersListViewModel.error.getOrAwaitValue()
        assertTrue(error)
    }

    private fun createUnSuccessfulResult(): ResultResponse<List<MCharacter>> {
        return ErrorResponse(
            ApiError(10, "General Error", "")
        )
    }
}