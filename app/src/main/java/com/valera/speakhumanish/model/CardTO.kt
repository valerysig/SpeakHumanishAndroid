package com.valera.speakhumanish.model

import com.valera.speakhumanish.common.splitNumbers

//TODO: Later on make the soundLocationID not an optional
/**
 * possibleChildren - If the property is null then the card is a static card
 */
data class CardTO (
    var id: Long,
    val label: String,
    val imageLocation: String,
    val soundLocation: String? = null,
    val possibleChildren: Set<Long>? = HashSet(),
    val isInitialCard : Boolean) {

    constructor(card : Card) : this(card.id,
        card.label,
        card.imageLocation,
        card.soundLocation,
        card.possibleChildren?.splitNumbers(","),
        card.isInitialCard)

    val imageLocationID : Int
        get() {
            return Supplier.contextActivity.resources.getIdentifier(
                this.imageLocation,
                "drawable",
                Supplier.contextActivity.packageName
            )
        }

    val soundLocationID : Int?
        get() {
            return if (this.soundLocation.equals("")) null else Supplier.contextActivity.resources.getIdentifier(
                this.soundLocation,
                "raw",
                Supplier.contextActivity.packageName
            )
        }

    val isStaticCard : Boolean
        get() {
            return possibleChildren == null
        }
}

