package com.valera.speakhumanish.model

import com.valera.speakhumanish.dao.AppDatabase
import javax.inject.Inject
import javax.inject.Named

class CardsDBParserImpl
    @Inject constructor(@Named("JSONCardParser") var parser: ICardsParser) : ICardsParser{

    private val mdb : AppDatabase

    init {
        //Init the DB here
        mdb = AppDatabase.getFileDatabase(Supplier.childScreenActivity)
        mdb.cardsModel().insertCard(parser.getAllAvailableCards().values.toList())

        var cards = mdb.cardsModel().getAllCards()
    }

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