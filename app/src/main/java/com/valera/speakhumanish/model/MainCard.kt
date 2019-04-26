package com.valera.speakhumanish.model

class MainCard(id: Int, label: String, imageLocation: Int, soundLocation: Int? = null, possibleChildren: Set<Int> = HashSet()) :
    Card(label, imageLocation, soundLocation)