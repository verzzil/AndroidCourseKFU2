package ru.itis.androidcoursekfu2.data.repositories

import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

interface AnimeMangaRepository {
    suspend fun getList(type: MediaType, pageNumber: Int): List<CardPresentation>
    suspend fun getPost(id: Int): CardPresentation
}