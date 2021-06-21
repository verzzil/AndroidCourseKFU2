package ru.itis.androidcoursekfu2.di

import android.app.Application
import ru.itis.androidcoursekfu2.di.component.AppComponent
import ru.itis.androidcoursekfu2.di.component.DaggerAppComponent

object Injector {
    private lateinit var appComponent: AppComponent

    fun init(app: Application) {
        appComponent = DaggerAppComponent.builder()
            .appModule(app)
            .build()
    }

    fun viewModelComponent() =
        appComponent.viewModelComponent().create()

}