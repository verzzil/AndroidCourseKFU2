package ru.itis.androidcoursekfu2.presentation.fragment

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.navArgs
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.di.component.ViewModelComponent
import ru.itis.androidcoursekfu2.presentation.MainActivity
import ru.itis.androidcoursekfu2.presentation.adapter.AnimeMangaAdapter
import ru.itis.androidcoursekfu2.presentation.fragment.anime.AnimeViewModel
import type.MediaType

class AnimeMangaDescriptionFragment : Fragment() {

    private lateinit var viewModel: AnimeMangaDescriptionViewModel
    private lateinit var viewModelComponent: ViewModelComponent
    private lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var navController: NavController
    private val args: AnimeMangaDescriptionFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as MainActivity).navController
        viewModelComponent = (activity as MainActivity).viewModelComponent
        viewModelFactory = (activity as MainActivity).viewModelFactory

        viewModelComponent.inject(this)
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(AnimeMangaDescriptionViewModel::class.java)
        return inflater.inflate(R.layout.fragment_anime_manga_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers(view)

        initQueries()
    }

    private fun initObservers(view: View) {
        viewModel.getCurrentPost().observe(viewLifecycleOwner, {
            view.findViewById<TextView>(R.id.full_title).text = "Title: ${it.title}"
            if (it.type == MediaType.ANIME) {
                view.findViewById<TextView>(R.id.full_count_episodes_chapters).text =
                    "Episodes in total: ${it.episodes}"
                view.findViewById<TextView>(R.id.full_duration_volume).text =
                    "Episode duration: ${it.episodeDuration} min"
            } else {
                view.findViewById<TextView>(R.id.full_count_episodes_chapters).text =
                    "Chapters in total: ${it.chapters}"
                view.findViewById<TextView>(R.id.full_duration_volume).text =
                    "Volumes in total: ${it.volumes}"
            }
            view.findViewById<TextView>(R.id.full_site_link).setOnClickListener { _ ->
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse(it.siteLink))
                )
            }
            view.findViewById<TextView>(R.id.full_desc).text = it.description
            if (it.fullImage != null)
                view.findViewById<ImageView>(R.id.full_image).setImageBitmap(it.fullImage)
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
        viewModel.findPost(args.postId)
    }

}