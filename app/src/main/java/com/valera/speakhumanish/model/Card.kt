package com.valera.speakhumanish.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "Cards", indices = arrayOf(Index(value = ["possible_children"])))
data class Card (
    @PrimaryKey var id: Long,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "image_location") val imageLocation: String,
    @ColumnInfo(name = "sound_location") val soundLocation: String? = null,
    @ColumnInfo(name = "possible_children") val possibleChildren: String? = "") {

    constructor(card : CardTO) : this(card.id,
        card.label,
        card.imageLocation,
        card.soundLocation,
        card.possibleChildren?.toString()?.replace("[", "")?.replace("]", "")?.replace(" ", ""))
}