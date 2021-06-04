package ru.itis.androidcoursekfu2.presentation

import CurrentMediaQuery
import GetListQuery
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.OkHttpClient
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.di.Injector
import ru.itis.androidcoursekfu2.di.component.ViewModelComponent
import type.MediaType
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var viewModelComponent: ViewModelComponent
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelComponent = Injector.viewModelComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // ViewModel inject
        viewModelComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        // UI
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment) as NavHostFragment
        navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.main_bottom_navigation).setupWithNavController(
            navController
        )

        val okHttpClient = OkHttpClient.Builder().build()
        val apollo = ApolloClient.builder().serverUrl("https://graphql.anilist.co")
            .okHttpClient(okHttpClient).build()

        val testQuery: CurrentMediaQuery = CurrentMediaQuery.builder()
            .id(20)
            .build()
        val dataQuery = GetListQuery.builder()
            .type(MediaType.ANIME)
            .countOnPage(10)
            .numberOfPage(1)
            .build()

        apollo?.query(testQuery)?.enqueue(
            object : ApolloCall.Callback<CurrentMediaQuery.Data>() {
                override fun onResponse(response: Response<CurrentMediaQuery.Data>) {
                    Log.i("asdfqwerqwer", "${response.data?.Media()} ${response.data?.Media()?.title()?.romaji()}")
                }

                override fun onFailure(e: ApolloException) {
                    e.printStackTrace()
                }
            }
        )

        apollo?.query(dataQuery)?.enqueue(
            object : ApolloCall.Callback<GetListQuery.Data>() {

                override fun onFailure(e: ApolloException) {
                    e.printStackTrace()
                }

                override fun onResponse(response: Response<GetListQuery.Data>) {
                    Log.i(
                        "asdfqwerqwer",
                        "${response.data?.Page()?.media()}"
                    )
                }
            }
        )

    }
}