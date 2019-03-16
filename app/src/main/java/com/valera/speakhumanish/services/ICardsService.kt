package com.valera.speakhumanish.services

import com.valera.speakhumanish.model.Card
import com.valera.speakhumanish.model.MainCard

interface ICardsService {
    fun getStaticCards(): List<Card>
    fun getMainCards(): List<MainCard>
    fun updatePresentingCards(selectedCard: Card)
    fun backOneLevel()
}