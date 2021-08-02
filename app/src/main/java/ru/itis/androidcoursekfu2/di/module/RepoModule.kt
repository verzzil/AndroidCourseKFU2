package ru.itis.androidcoursekfu2.di.module

import dagger.Module
import dagger.Provides
import ru.itis.androidcoursekfu2.data.api.AnimeListApi
import ru.itis.androidcoursekfu2.data.repositories.AnimeMangaRepository
import ru.itis.androidcoursekfu2.data.repositories.AnimeMangaRepositoryImpl
import javax.inject.Singleton

@Module
class RepoModule {
    @Provides
    @Singleton
    fun provideAnimeMangaRepository(
        api: AnimeListApi
    ): AnimeMangaRepository =
        AnimeMangaRepositoryImpl(api)
}