package com.valera.speakhumanish.model

interface ICardsParser {
    fun getStaticCards() : List<CardTO>
    fun getInitialMainCards() : List<CardTO>
    fun getAllAvailableCards() : Map<Long, CardTO>
}