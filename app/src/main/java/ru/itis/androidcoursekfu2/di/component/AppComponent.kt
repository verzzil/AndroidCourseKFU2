package ru.itis.androidcoursekfu2.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.itis.androidcoursekfu2.di.module.AppModule
import ru.itis.androidcoursekfu2.di.module.DomainModule
import ru.itis.androidcoursekfu2.di.module.NetModule
import ru.itis.androidcoursekfu2.di.module.RepoModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class,
        DomainModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun viewModelComponent(): ViewModelComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun appModule(application: Application): Builder

        fun build(): AppComponent
    }
}