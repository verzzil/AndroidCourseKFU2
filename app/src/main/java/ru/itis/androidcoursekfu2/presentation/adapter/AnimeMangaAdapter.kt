package ru.itis.androidcoursekfu2.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.presentation.models.CardPresentation
import type.MediaType

class AnimeMangaAdapter(
    private val click: (CardPresentation) -> Unit
) : ListAdapter<CardPresentation, AnimeMangaAdapter.AnimeMangaHolder>(
    object : DiffUtil.ItemCallback<CardPresentation>() {
        override fun areItemsTheSame(
            oldItem: CardPresentation,
            newItem: CardPresentation
        ): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CardPresentation,
            newItem: CardPresentation
        ): Boolean =
            oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeMangaHolder =
        AnimeMangaHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_anime_manga, parent, false),
            click
        )

    override fun onBindViewHolder(holder: AnimeMangaHolder, position: Int) =
        holder.bind(getItem(position))

    inner class AnimeMangaHolder(
        containerView: View,
        private val click: (CardPresentation) -> Unit
    ): RecyclerView.ViewHolder(containerView) {
        val image: ImageView = itemView.findViewById(R.id.item_anime_manga_image)
        val title: TextView = itemView.findViewById(R.id.item_anime_manga_title)
        val episodesChapters: TextView = itemView.findViewById(R.id.item_anime_manga_episodes_chapters)

        fun bind(item: CardPresentation) {
            if (item.cardImage != null)
                image.setImageBitmap(item.cardImage)
            title.text = item.title
            if (item.type == MediaType.ANIME)
                episodesChapters.text = "${item.episodes} episodes in total"
            else
                episodesChapters.text = "${item.chapters} chapters in total"

            itemView.setOnClickListener {
                click(item)
            }
        }
    }
}