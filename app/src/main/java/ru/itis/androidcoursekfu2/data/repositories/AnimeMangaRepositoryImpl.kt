package ru.itis.androidcoursekfu2.data.repositories

import ru.itis.androidcoursekfu2.data.api.AnimeListApi
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType
import java.lang.Exception
import javax.inject.Inject

class AnimeMangaRepositoryImpl(
    private val animeListApi: AnimeListApi
) : AnimeMangaRepository {
    override suspend fun getList(type: MediaType, pageNumber: Int): List<CardPresentation> {
        val apiResp = animeListApi.getList(type, pageNumber)
        if (apiResp != null)
            return CardPresentation.fromMediumList(apiResp)
        else
            throw Exception("Что-то не получилось(")
    }

    override suspend fun getPost(id: Int): CardPresentation {
        val apiResp = animeListApi.getPost(id)
        if (apiResp != null)
            return CardPresentation.from(apiResp)
        else
            throw Exception("Что-то не получилось(")
    }
}