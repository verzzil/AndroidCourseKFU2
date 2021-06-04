package ru.itis.androidcoursekfu2.presentation.fragment

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidcoursekfu2.domain.AnimeMangaUseCase
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import java.lang.Exception
import java.net.URL
import javax.inject.Inject

class AnimeMangaDescriptionViewModel @Inject constructor(
    private val animeMangaUseCase: AnimeMangaUseCase
) : ViewModel() {

    private val currentPost: MutableLiveData<CardPresentation> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()
    private val progress: MutableLiveData<Boolean> = MutableLiveData()

    fun findPost(id: Int) {
        progress.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val resp = animeMangaUseCase.getPost(id)
                    try {
                        resp.fullImage = BitmapFactory.decodeStream(
                            URL(resp.fullImageLink).openConnection()
                                .getInputStream())
                    } catch (e: Exception) {
                        e.printStackTrace()
                        errors.postValue(e)
                    }
                    currentPost.postValue(resp)
                } catch (e: Exception) {
                    e.printStackTrace()
                    errors.postValue(e)
                } finally {
                    progress.postValue(false)
                }
            }
        }
    }

    fun getCurrentPost(): MutableLiveData<CardPresentation> = currentPost
    fun getErrors(): MutableLiveData<Exception> = errors
    fun getProgress(): MutableLiveData<Boolean> = progress


}