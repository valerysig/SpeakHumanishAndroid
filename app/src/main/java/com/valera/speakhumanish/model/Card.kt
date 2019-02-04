package com.valera.speakhumanish.model

open class Card (val label: String, val imageLocation: Int, val soundLocation: String = "") {
    fun play() {
        //Play some sound
    }
}