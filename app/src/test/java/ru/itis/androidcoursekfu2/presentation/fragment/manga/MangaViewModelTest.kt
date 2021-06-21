package ru.itis.androidcoursekfu2.presentation.fragment.manga

import androidx.lifecycle.MutableLiveData
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import ru.itis.androidcoursekfu2.domain.AnimeMangaUseCase
import ru.itis.androidcoursekfu2.presentation.fragment.AnimeMangaDescriptionViewModel
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

internal class MangaViewModelTest {

    @MockK
    lateinit var animeMangaUseCase: AnimeMangaUseCase

    @MockK
    lateinit var progress: MutableLiveData<Boolean>
    @MockK
    lateinit var mangaList: MutableLiveData<List<CardPresentation>>

    private lateinit var viewModel: MangaViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(MangaViewModel(animeMangaUseCase))
        viewModel.setMangaList(mangaList)
        viewModel.setProgress(progress)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun findManga() {
        //Arrange
        val expectedList = mockk<List<CardPresentation>>()
        val expectedType = mockk<MediaType>()
        val expectedPageNumber = 2
        coEvery { animeMangaUseCase.getList(expectedType, expectedPageNumber) } returns mockk()
        //Act
        viewModel.findManga()
        //Assert
        verify {
            progress.value = true
            mangaList.postValue(expectedList)
            progress.postValue(false)
        }
    }
}