package com.valera.speakhumanish.Model

open class Card (val label: String, val imageLocation: String, val soundLocation: String = "") {
    fun play() {
        //Play some sound
    }
}