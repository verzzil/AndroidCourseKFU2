package ru.itis.androidcoursekfu2.data.api

import CurrentMediaQuery
import GetListQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.toDeferred
import ru.itis.androidcoursekfu2.utils.Consts
import type.MediaType
import javax.inject.Inject

class AnimeListApiImpl(
    private val aniListApi: ApolloClient
) : AnimeListApi {
    override suspend fun getList(type: MediaType, pageNumber: Int): List<GetListQuery.Medium>? {
        val query = GetListQuery.builder()
            .type(type)
            .countOnPage(Consts.COUNT_ON_PAGE)
            .numberOfPage(pageNumber)
            .build()
        val deferred = aniListApi.query(query).toDeferred()
        val response = deferred.await()
        return response.data?.Page()?.media()
    }

    override suspend fun getPost(id: Int): CurrentMediaQuery.Media? {
        val query = CurrentMediaQuery.builder()
            .id(id)
            .build()
        val deffered = aniListApi.query(query).toDeferred()
        val resp = deffered.await()
        return resp.data?.Media()
    }
}