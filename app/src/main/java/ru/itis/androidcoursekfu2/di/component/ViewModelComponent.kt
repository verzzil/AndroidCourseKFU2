package ru.itis.androidcoursekfu2.di.component

import dagger.Subcomponent
import ru.itis.androidcoursekfu2.di.module.ViewModelModule

@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ViewModelComponent
    }

}