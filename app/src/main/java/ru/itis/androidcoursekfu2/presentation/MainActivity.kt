package ru.itis.androidcoursekfu2.presentation

import GetListQuery
import TestQuery
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import type.MediaType

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val testQuery: TestQuery = TestQuery.builder()
            .id(20)
            .build()
        val dataQuery = GetListQuery.builder()
            .type(MediaType.ANIME)
            .countOnPage(10)
            .numberOfPage(1)
            .build()

        apollo?.query(testQuery)?.enqueue(
            object : ApolloCall.Callback<TestQuery.Data>() {
                override fun onResponse(response: Response<TestQuery.Data>) {
//                    Log.i("asdfqwerqwer", "${response.data?.Media()} ${response.data?.Media()?.title()?.english()}")
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
                        "${response.data?.Page()}"
                    )

                }
            }
        )

    }
}