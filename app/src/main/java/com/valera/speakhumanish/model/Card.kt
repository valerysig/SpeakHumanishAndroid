package com.valera.speakhumanish.model

import android.media.MediaPlayer

//TODO: Later on make the soundLocation not an optional
open class Card (val label: String, val imageLocation: Int, val soundLocation: Int? = null)