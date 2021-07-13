package ru.itis.androidcoursekfu2.domain

import ru.itis.androidcoursekfu2.data.repositories.AnimeMangaRepository
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

class AnimeMangaUseCaseImpl(
    private val animeMangaRepository: AnimeMangaRepository
) : AnimeMangaUseCase {
    override suspend fun getList(type: MediaType, pageNumber: Int): List<CardPresentation> {
        return animeMangaRepository.getList(type, pageNumber)
    }

    override suspend fun getPost(id: Int): CardPresentation {
        return animeMangaRepository.getPost(id)
    }
}