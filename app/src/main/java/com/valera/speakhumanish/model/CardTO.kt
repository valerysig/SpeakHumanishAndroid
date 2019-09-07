package com.valera.speakhumanish.model

import com.valera.speakhumanish.common.splitNumbers
import java.io.Serializable

//TODO: Later on make the soundLocationID not an optional
/**
 * possibleChildren - If the property is null then the card is a static card
 */
data class CardTO (
    var id: Long,
    var label: String,
    var imageLocation: String,
    var soundLocation: String? = null,
    val possibleChildren: Set<Long>? = HashSet(),
    val isInitialCard : Boolean,
    var imageUri : String? = null) : Serializable {

    constructor(card : Card) : this(card.id,
        card.label,
        card.imageLocation,
        card.soundLocation,
        card.possibleChildren?.splitNumbers(","),
        card.isInitialCard,
        card.imageUri)

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

