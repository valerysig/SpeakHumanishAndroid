package com.valera.speakhumanish.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cards")
data class Card (
    @PrimaryKey var id: Long,
    val label: String,
    val imageLocation: String,
    val soundLocation: String? = null,
    val possibleChildren: String? = "") {

    constructor(card : CardTO) : this(card.id,
        card.label,
        card.imageLocation,
        card.soundLocation,
        card.possibleChildren?.toString()?.replace("[", "")?.replace("]", "")?.replace(" ", ""))
}