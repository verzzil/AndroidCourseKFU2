package ru.itis.androidcoursekfu2.presentation.models

import GetListQuery
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import type.MediaType
import java.net.URL

data class CardPresentation(
    val id: Int?,
    val title: String?,
    val type: MediaType?,
    val description: String?,
    val episodes: Int?,
    val episodeDuration: Int?,
    val chapters: Int?,
    val volumes: Int?,
    val cardImageLink: String?,
    var cardImage: Bitmap?,
    val fullImageLink: String?,
    var fullImage: Bitmap?,
    val siteLink: String?
) {
    companion object {
        fun from(media: GetListQuery.Medium): CardPresentation {
            return CardPresentation(
                media.id(),
                media.title()?.romaji(),
                media.type(),
                media.description(),
                media.episodes(),
                media.duration(),
                media.chapters(),
                media.volumes(),
                media.coverImage()?.extraLarge(),
                null,
                media.bannerImage(),
                null,
                media.siteUrl()
            )
        }

        fun fromMediumList(medias: List<GetListQuery.Medium>): List<CardPresentation> =
            medias.map(::from)

        fun from(media: CurrentMediaQuery.Media): CardPresentation {
            return CardPresentation(
                media.id(),
                media.title()?.romaji(),
                media.type(),
                media.description(),
                media.episodes(),
                media.duration(),
                media.chapters(),
                media.volumes(),
                media.coverImage()?.extraLarge(),
                null,
                media.bannerImage(),
                null,
                media.siteUrl()
            )
        }
        fun setupImages(cards: List<CardPresentation>) {
            cards.map { cardPresentation -> cardPresentation.setupImages() }
        }
    }

    fun setupImages() {
        this.cardImage = BitmapFactory.decodeStream(
            URL(this.cardImageLink).openConnection()
                .getInputStream()
        )
        this.fullImage = BitmapFactory.decodeStream(
            URL(this.fullImageLink).openConnection()
                .getInputStream()
        )
    }

}