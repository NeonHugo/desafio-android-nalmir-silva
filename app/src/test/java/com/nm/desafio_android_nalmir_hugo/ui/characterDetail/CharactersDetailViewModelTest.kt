package com.nm.desafio_android_nalmir_hugo.ui.characterDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nm.desafio_android_nalmir_hugo.ui.charactersList.CharactersListViewModel
import com.nm.domain.entity.MCharacter
import com.nm.domain.usecase.MCharacterUseCase
import com.nm.infrastructure.net.Network
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

class CharactersDetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @MockK
    lateinit var network: Network

    lateinit var charactersDetailViewModel: CharactersDetailViewModel

    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)

        MockKAnnotations.init(this)

        startKoin {
        }

        charactersDetailViewModel = CharactersDetailViewModel(network)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
        stopKoin()
    }

    @Test
    fun successfulCharacter() {
        charactersDetailViewModel.onCharacterDetail(createCharacter())

        val character = charactersDetailViewModel.character.getOrAwaitValue()
        assertEquals(createCharacter(), character)
    }

    private fun createCharacter(): MCharacter {
        return MCharacter(10L, "Hulk", "Paciente", "")
    }


}