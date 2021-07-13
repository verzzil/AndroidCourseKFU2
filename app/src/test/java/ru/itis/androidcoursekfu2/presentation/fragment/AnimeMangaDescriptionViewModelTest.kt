package ru.itis.androidcoursekfu2.presentation.fragment

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
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation

internal class AnimeMangaDescriptionViewModelTest {

    @MockK
    lateinit var animeMangaUseCase: AnimeMangaUseCase

    @MockK
    lateinit var progress: MutableLiveData<Boolean>
    @MockK
    lateinit var currentPost: MutableLiveData<CardPresentation>

    private lateinit var viewModel: AnimeMangaDescriptionViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(AnimeMangaDescriptionViewModel(animeMangaUseCase))
        viewModel.setCurrentPost(currentPost)
        viewModel.setProgress(progress)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun findPost() {
        //Arrange
        val expectedId = 1
        val expectedResp = mockk<CardPresentation>()
        coEvery { animeMangaUseCase.getPost(expectedId) } returns expectedResp
        //Act
        viewModel.findPost(expectedId)
        //Assert
        verify {
            progress.value = true
            currentPost.postValue(expectedResp)
            progress.postValue(false)
        }
    }

}