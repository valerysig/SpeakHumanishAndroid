package com.valera.speakhumanish.common

import android.net.Uri
import android.widget.ImageView
import com.valera.speakhumanish.model.CardTO

fun String.splitNumbers(delimiter : String) : Set<Long> {
    if (this == "") {
        return HashSet()
    }
    return this.split(delimiter).map { t -> t.toLong() }.toSet()
}

fun ImageView.setImageSrc(card : CardTO) {
    if (card.imageUri != null) {
        this.setImageURI(Uri.parse(card.imageUri))
    } else {
        this.setImageResource(card.imageLocationID)
    }
}