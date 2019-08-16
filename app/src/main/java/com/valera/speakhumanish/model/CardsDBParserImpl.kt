package com.valera.speakhumanish.model

import com.valera.speakhumanish.dao.AppDatabase
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CardsDBParserImpl
    @Inject constructor(
        @Named("JSONCardParser") var parser: ICardsParser) : ICardsParser{

    private val mdb : AppDatabase

    init {
        AppDatabase.destroyInstance()
        mdb = AppDatabase.getFileDatabase(Supplier.contextActivity)
        val initDBThread = Thread { initDB() }
        initDBThread.start()

        initDBThread.join()
    }

    override fun getStaticCards(): List<CardTO> {
        var cards : List<CardTO> = ArrayList()
        val dbThread = Thread {
            cards = mdb.cardsModel().getStaticCards().map { CardTO(it) }.toList()
        }
        dbThread.start()
        dbThread.join()
        return cards
    }

    override fun getInitialMainCards(): List<CardTO> {
        var initialCards : List<CardTO> = ArrayList()
        val dbThread = Thread {
            initialCards = mdb.cardsModel().getInitialCards().map { CardTO(it) }.toList()
        }
        dbThread.start()
        dbThread.join()
        return initialCards
    }

    override fun getAllAvailableCards(): Map<Long, CardTO> {

        var cardsMap : Map<Long, CardTO> = HashMap()
        val dbThread = Thread {
             cardsMap = mdb.cardsModel().getAllCards().map { it.id to CardTO(it) }.toMap()
        }
        dbThread.start()
        dbThread.join()

        return cardsMap
    }

    //region Private methods
    /**
     * Must run on another thread
     */
    private fun initDB() {
        val allCards = parser.getAllAvailableCards().values.map { c -> Card(c) }.toList()

        mdb.cardsModel().insertCard(allCards)
    }


    //endregion
}