package ru.itis.androidcoursekfu2.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import ru.itis.androidcoursekfu2.utils.AppViewModelFactory

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}