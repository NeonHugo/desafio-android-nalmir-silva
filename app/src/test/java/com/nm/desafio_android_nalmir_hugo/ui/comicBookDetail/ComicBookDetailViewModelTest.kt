package com.nm.desafio_android_nalmir_hugo.ui.comicBookDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nm.domain.entity.Comic
import com.nm.domain.usecase.ComicUseCase
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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class ComicBookDetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI Thread")

    @MockK
    lateinit var comicUseCase: ComicUseCase

    @MockK
    lateinit var network: Network

    lateinit var comicBookDetailViewModel: ComicBookDetailViewModel

    @Before
    fun setupTest() {
        Dispatchers.setMain(mainThreadSurrogate)

        MockKAnnotations.init(this)

        startKoin {
        }

        comicBookDetailViewModel = ComicBookDetailViewModel(comicUseCase, network)
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
                comicUseCase.showComic(
                    any(), any(), any(), any()
                )
            }
        } returns createComicBookResponse()

        comicBookDetailViewModel.onComicDetail(10L)

        val comic = comicBookDetailViewModel.comic.getOrAwaitValue()
        assertEquals(createComicBook(), comic)
    }

    private fun createComicBookResponse(): ResultResponse<Comic> {
        return SuccessResponse(createComicBook())
    }

    private fun createComicBook(): Comic {
        return Comic(10L, "Hulk", "Paciente", 20.90, "")
    }

    @Test
    fun unSuccessfulCharacterList() {
        every {
            runBlocking {
                comicUseCase.showComic(
                    any(), any(), any(), any()
                )
            }
        } returns createUnSuccessfulResult()

        comicBookDetailViewModel.onComicDetail(10L)

        val error = comicBookDetailViewModel.error.getOrAwaitValue()
        assertTrue(error)
    }

    private fun createUnSuccessfulResult(): ResultResponse<Comic> {
        return ErrorResponse(
            ApiError(10, "General Error", "")
        )
    }


}