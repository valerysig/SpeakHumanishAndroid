package com.valera.speakhumanish.model

//TODO: Maybe remove this class altogether
class MainCard(id: Long, label: String, imageLocation: Int, soundLocation: Int? = null, possibleChildren: Set<Long> = HashSet()) :
    Card(id, label, imageLocation, soundLocation)