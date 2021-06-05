package ru.itis.androidcoursekfu2.domain

import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import ru.itis.androidcoursekfu2.data.repositories.AnimeMangaRepository
import ru.itis.androidcoursekfu2.presentation.fragment.manga.MangaViewModel
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

internal class AnimeMangaUseCaseImplTest {

    @MockK
    lateinit var animeMangaRepository: AnimeMangaRepository

    private lateinit var animeMangaUseCase: AnimeMangaUseCase

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        animeMangaUseCase = spyk(AnimeMangaUseCaseImpl(animeMangaRepository))
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    suspend fun getList() {
        // Arrange
        val expectedType = mockk<MediaType>()
        val expectedPageNumber = 1
        val expectedList = mockk<List<CardPresentation>>()
        //Act
        animeMangaUseCase.getList(expectedType, expectedPageNumber)
        //Assert
        assertEquals(expectedList, expectedList)
    }

    @Test
    suspend fun getPost() {
        // Arrange
        val expectedPostId = 1
        val expectedPost = mockk<CardPresentation>()
        //Act
        animeMangaUseCase.getPost(expectedPostId)
        //Assert
        assertEquals(expectedPost, expectedPost)
    }
}