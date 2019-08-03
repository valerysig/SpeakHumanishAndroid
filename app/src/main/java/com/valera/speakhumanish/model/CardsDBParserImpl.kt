package com.valera.speakhumanish.model

import javax.inject.Inject
import javax.inject.Named

class CardsDBParserImpl
    @Inject constructor() : ICardsParser{

    @Inject
    @field:Named("JSONCardParser")
    lateinit var parser: ICardsParser

    override fun getStaticCards(): List<Card> {
        return parser.getStaticCards()
    }

    override fun getInitialMainCards(): List<Card> {
        return parser.getInitialMainCards()
    }

    override fun getAllAvailableCards(): Map<Long, Card> {
        return parser.getAllAvailableCards()
    }
}