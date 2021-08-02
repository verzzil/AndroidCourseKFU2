package ru.itis.androidcoursekfu2.data.api

import CurrentMediaQuery
import GetListQuery
import type.MediaType

interface AnimeListApi {
    suspend fun getList(type: MediaType, pageNumber: Int): List<GetListQuery.Medium>?
    suspend fun getPost(id: Int): CurrentMediaQuery.Media?
}