package com.valera.speakhumanish.Model

class MainCard(label: String, imageLocation: String, soundLocation: String = "", possibleChildren: Set<Int> = HashSet()) :
    Card(label, imageLocation, soundLocation)