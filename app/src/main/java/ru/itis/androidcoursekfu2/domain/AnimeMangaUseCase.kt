package ru.itis.androidcoursekfu2.domain

import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

interface AnimeMangaUseCase {
    suspend fun getList(type: MediaType, pageNumber: Int): List<CardPresentation>
    suspend fun getPost(id: Int): CardPresentation
}