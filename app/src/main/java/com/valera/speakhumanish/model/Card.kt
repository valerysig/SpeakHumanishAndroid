package com.valera.speakhumanish.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO: Later on make the soundLocationID not an optional
/**
 * possibleChildren - If the property is null then the card is a static card
 */
@Entity(tableName = "Cards")
data class Card (
    @PrimaryKey var id: Long,
    val label: String,
    val imageLocation: String,
    val soundLocation: String? = null,
    val possibleChildren: Set<Long>? = HashSet()) {

    var imageLocationID : Int = 0
    var soundLocationID : Int? = null
}

