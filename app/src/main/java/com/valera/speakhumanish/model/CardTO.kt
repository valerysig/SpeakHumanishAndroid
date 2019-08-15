package com.valera.speakhumanish.model

//TODO: Later on make the soundLocationID not an optional
/**
 * possibleChildren - If the property is null then the card is a static card
 */
data class CardTO (
    var id: Long,
    val label: String,
    val imageLocation: String,
    val soundLocation: String? = null,
    val possibleChildren: Set<Long>? = HashSet()) {

    var imageLocationID : Int = 0
    var soundLocationID : Int? = null

    constructor(card : Card) : this(card.id,
        card.label,
        card.imageLocation,
        card.soundLocation,
        card.possibleChildren?.split(",")?.map { t -> t.toLong() }?.toSet())
}

