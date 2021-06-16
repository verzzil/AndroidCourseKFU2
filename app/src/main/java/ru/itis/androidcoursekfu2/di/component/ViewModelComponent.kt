package ru.itis.androidcoursekfu2.di.component

import dagger.Subcomponent
import ru.itis.androidcoursekfu2.di.ActivityScope
import ru.itis.androidcoursekfu2.di.module.ViewModelFactoryModule
import ru.itis.androidcoursekfu2.di.module.ViewModelModule
import ru.itis.androidcoursekfu2.presentation.MainActivity
import ru.itis.androidcoursekfu2.presentation.fragment.AnimeMangaDescriptionFragment
import ru.itis.androidcoursekfu2.presentation.fragment.anime.AnimeFragment
import ru.itis.androidcoursekfu2.presentation.fragment.manga.MangaFragment

@Subcomponent(modules = [
    ViewModelFactoryModule::class,
    ViewModelModule::class,
])
@ActivityScope
interface ViewModelComponent {

    fun inject(activity: MainActivity)
    fun inject(fragment: AnimeFragment)
    fun inject(fragment: MangaFragment)
    fun inject(fragment: AnimeMangaDescriptionFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}