package com.valera.speakhumanish.services

import com.valera.speakhumanish.model.CardTO

interface ICardsService {
    val lastPressedCard : CardTO?
        get() = if (getPressedCards().isEmpty()) null else getPressedCards().last()

    fun getCard(cardId : Long) : CardTO
    fun getStaticCards(): List<CardTO>
    fun getMainCards(): List<CardTO>
    fun getPressedCards() : List<CardTO>
    fun updatePresentingCards(selectedCardId: Long)
    fun backOneLevel()
    fun clear()
}