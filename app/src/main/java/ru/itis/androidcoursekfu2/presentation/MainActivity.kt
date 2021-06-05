package ru.itis.androidcoursekfu2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.di.Injector
import ru.itis.androidcoursekfu2.di.component.ViewModelComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var viewModelComponent: ViewModelComponent

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = Injector.viewModelComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // UI
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.main_bottom_navigation).setupWithNavController(
            navController
        )

    }
}