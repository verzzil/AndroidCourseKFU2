package ru.itis.androidcoursekfu2.presentation.fragment.anime

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.di.component.ViewModelComponent
import ru.itis.androidcoursekfu2.presentation.MainActivity
import ru.itis.androidcoursekfu2.presentation.adapter.AnimeMangaAdapter


class AnimeFragment : Fragment() {

    private lateinit var viewModel: AnimeViewModel
    private lateinit var viewModelComponent: ViewModelComponent
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var animeAdapter: AnimeMangaAdapter
    private lateinit var navController: NavController
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as MainActivity).navController
        viewModelComponent = (activity as MainActivity).viewModelComponent
        viewModelFactory = (activity as MainActivity).viewModelFactory

        viewModelComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AnimeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_anime_manga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initQueries()

        initUi(view)
    }

    private fun initObservers() {
        viewModel.getAnimeList().observe(viewLifecycleOwner, {
            animeAdapter.submitList(it)
            isLoading = false
        })
        viewModel.getErrors().observe(viewLifecycleOwner, {
            Toast.makeText(
                (activity as MainActivity),
                "Скорее всего, у вас нет интернета",
                Toast.LENGTH_SHORT
            ).show()
        })
        viewModel.getProgress().observe(viewLifecycleOwner, {
            if (it == true) {
                (activity as MainActivity).findViewById<ProgressBar>(R.id.pb_main).visibility =
                    View.VISIBLE
            } else {
                (activity as MainActivity).findViewById<ProgressBar>(R.id.pb_main).visibility =
                    View.GONE
            }
        })
    }

    private fun initQueries() {
        if (viewModel.getAnimeList().value?.size == null)
            viewModel.findAnime()
    }


    private fun initUi(view: View) {
        initAdapter(view)
    }

    private fun initAdapter(view: View) {
        animeAdapter = AnimeMangaAdapter {
            navController.navigate(
                AnimeFragmentDirections.actionAnimeFragmentToAnimeMangaDescriptionFragment(
                    it.id ?: -1
                )
            )
        }
        val rvAnime: RecyclerView = view.findViewById(R.id.anime_rv)
        rvAnime.adapter = animeAdapter
        val rvManager = rvAnime.layoutManager as LinearLayoutManager
        rvAnime.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount: Int = rvManager.itemCount
                val lastVisibleItemPosition: Int = rvManager.findLastVisibleItemPosition()

                if (!isLoading && totalItemCount - 1 == lastVisibleItemPosition) {
                    isLoading = true
                    viewModel.findAnime()
                }
            }
        })
    }

}