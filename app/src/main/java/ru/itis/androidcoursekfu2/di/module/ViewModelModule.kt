package ru.itis.androidcoursekfu2.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.itis.androidcoursekfu2.di.ViewModelKey
import ru.itis.androidcoursekfu2.presentation.fragment.AnimeMangaDescriptionViewModel
import ru.itis.androidcoursekfu2.presentation.fragment.anime.AnimeViewModel
import ru.itis.androidcoursekfu2.presentation.fragment.manga.MangaViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AnimeViewModel::class)
    fun bindAnimeViewModel(viewModel: AnimeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MangaViewModel::class)
    fun bindMangaViewModel(viewModel: MangaViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnimeMangaDescriptionViewModel::class)
    fun bindAnimeMangaDescriptionViewModel(viewModel: AnimeMangaDescriptionViewModel): ViewModel

}