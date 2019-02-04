package com.valera.speakhumanish.Model

class MainCard(id: Int, label: String, imageLocation: Int, soundLocation: String = "", possibleChildren: Set<Int> = HashSet()) :
    Card(label, imageLocation, soundLocation)