package com.valera.speakhumanish.model

import com.valera.speakhumanish.dao.AppDatabase
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CardsDBParserImpl
    @Inject constructor(@Named("JSONCardParser") var parser: ICardsParser) : ICardsParser{

    private val mdb : AppDatabase
    private val cards : Map<Long, CardTO>

    init {
        mdb = AppDatabase.getFileDatabase(Supplier.childScreenActivity)
        cards = HashMap()
        val initDBThread = Thread { initDB() }
        initDBThread.start()

        initDBThread.join()

        //TODO: Insert the cards into the member

    }

    override fun getStaticCards(): List<CardTO> {
        //TODO: return the cards from the member
        return parser.getStaticCards()
    }

    override fun getInitialMainCards(): List<CardTO> {
        //TODO: return the cards from the member
        return parser.getInitialMainCards()
    }

    override fun getAllAvailableCards(): Map<Long, CardTO> {
        //TODO: return the cards from the member
        return parser.getAllAvailableCards()
    }

    //region Private methods
    private fun initDB() {
        val allCards = parser.getAllAvailableCards().values.map { c -> Card(c) }.toList()

        mdb.cardsModel().insertCard(allCards)
    }
    //endregion
}