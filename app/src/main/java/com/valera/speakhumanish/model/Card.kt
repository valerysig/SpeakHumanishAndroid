package com.valera.speakhumanish.model

import com.google.gson.annotations.SerializedName

//TODO: Later on make the soundLocationID not an optional
/**
 * possibleChildren - If the property is null then the card is a static card
 */
data class Card (
    var id: Long,
    @SerializedName("lable")
    val label: String,
    @SerializedName("imageLocation")
    val imageLocationStr: String,
    @SerializedName("soundLocation")
    val soundLocationStr: String? = null,
    @SerializedName("possibleChildren")
    val possibleChildren: Set<Long>? = HashSet()) {

    var imageLocationID : Int = 0
    var soundLocationID : Int? = null
}

