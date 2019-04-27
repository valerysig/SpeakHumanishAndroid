package com.valera.speakhumanish.services

import com.valera.speakhumanish.model.Card

interface ICardsService {
    fun getStaticCards(): List<Card>
    fun getMainCards(): List<Card>
    fun getPressedCards() : List<Card>
    fun updatePresentingCards(selectedCardId: Long)
    fun backOneLevel()
    fun clear()
}