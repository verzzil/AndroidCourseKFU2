package ru.itis.androidcoursekfu2.di.module

import dagger.Module
import dagger.Provides
import ru.itis.androidcoursekfu2.data.repositories.AnimeMangaRepository
import ru.itis.androidcoursekfu2.domain.AnimeMangaUseCase
import ru.itis.androidcoursekfu2.domain.AnimeMangaUseCaseImpl
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideAnimeMangaUseCase(
        animeMangaRepository: AnimeMangaRepository
    ): AnimeMangaUseCase =
        AnimeMangaUseCaseImpl(animeMangaRepository)

}