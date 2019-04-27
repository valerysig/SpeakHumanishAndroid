package com.valera.speakhumanish.services

import android.util.Log
import com.valera.speakhumanish.model.Card
import javax.inject.Inject

class CardsServiceImpl
    @Inject constructor() : ICardsService {


    override fun getStaticCards(): List<Card> {
        Log.d("CardsServiceImpl", "getStaticCards")
        return listOf()
    }

    override fun getMainCards(): List<Card> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updatePresentingCards(selectedCard: Card) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun backOneLevel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}