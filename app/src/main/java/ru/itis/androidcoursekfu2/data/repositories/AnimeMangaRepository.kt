package ru.itis.androidcoursekfu2.data.repositories

import kotlinx.coroutines.flow.Flow
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

interface AnimeMangaRepository {
    suspend fun getList(type: MediaType, pageNumber: Int): Flow<CardPresentation>
    suspend fun getPost(id: Int): CardPresentation
}