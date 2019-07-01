package com.valera.speakhumanish.model

interface ICardsParser {
    fun getStaticCards() : List<Card>
    fun getInitialMainCards() : List<Card>
    fun getAllAvailableCards() : Map<Long, Card>
}