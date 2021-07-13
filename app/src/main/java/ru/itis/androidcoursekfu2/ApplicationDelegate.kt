package ru.itis.androidcoursekfu2

import android.app.Application
import android.util.Log
import ru.itis.androidcoursekfu2.di.Injector

class ApplicationDelegate: Application() {
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}