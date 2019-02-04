package com.valera.speakhumanish.model

class MainCard(id: Int, label: String, imageLocation: Int, soundLocation: String = "", possibleChildren: Set<Int> = HashSet()) :
    Card(label, imageLocation, soundLocation)