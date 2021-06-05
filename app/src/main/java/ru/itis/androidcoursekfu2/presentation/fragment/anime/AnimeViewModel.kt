package ru.itis.androidcoursekfu2.presentation.fragment.anime

import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.itis.androidcoursekfu2.domain.AnimeMangaUseCase
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType
import java.lang.Exception
import java.net.URL
import javax.inject.Inject

class AnimeViewModel @Inject constructor(
    private val animeMangaUseCase: AnimeMangaUseCase
) : ViewModel() {

    private var animeList: MutableLiveData<List<CardPresentation>> = MutableLiveData()
    private val errors: MutableLiveData<Exception> = MutableLiveData()
    private var progress: MutableLiveData<Boolean> = MutableLiveData(false)
    private var currentPage = 1
    private var currentList = ArrayList<CardPresentation>()

    fun findAnime() {
        progress.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val resp = animeMangaUseCase.getList(MediaType.ANIME, currentPage++)
                    try {
                        for(car: CardPresentation in resp) {
                            car.cardImage = BitmapFactory.decodeStream(
                                URL(car.cardImageLink).openConnection()
                                    .getInputStream())
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        errors.postValue(e)
                    }

                    currentList =
                        (currentList + (resp as ArrayList<CardPresentation>)) as ArrayList<CardPresentation>

                    animeList.postValue(currentList)
                } catch (e: Exception) {
                    e.printStackTrace()
                    errors.postValue(e)
                } finally {
                    progress.postValue(false)
                }
            }
        }
    }

    fun getAnimeList(): MutableLiveData<List<CardPresentation>> = animeList
    fun getErrors(): MutableLiveData<Exception> = errors
    fun getProgress(): MutableLiveData<Boolean> = progress

    //for testing
    fun setAnimeList(list: MutableLiveData<List<CardPresentation>>) {
        animeList = list
    }
    fun setProgress(progress: MutableLiveData<Boolean>) {
        this.progress = progress
    }
}