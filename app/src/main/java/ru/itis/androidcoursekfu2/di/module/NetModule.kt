package ru.itis.androidcoursekfu2.di.module

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.androidcoursekfu2.data.api.AnimeListApi
import ru.itis.androidcoursekfu2.data.api.AnimeListApiImpl
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetModule {
    @Provides
    @Singleton
    fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideApollo(
        client: OkHttpClient
    ): ApolloClient =
        ApolloClient.builder()
            .okHttpClient(client)
            .serverUrl("https://graphql.anilist.co")
            .build()

    @Provides
    @Singleton
    fun provideAniListApi(
        client: ApolloClient
    ): AnimeListApi =
        AnimeListApiImpl(client)
}