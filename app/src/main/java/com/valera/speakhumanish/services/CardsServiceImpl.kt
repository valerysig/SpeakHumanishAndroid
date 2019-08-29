package com.valera.speakhumanish.services

import android.util.Log
import com.valera.speakhumanish.model.CardTO
import com.valera.speakhumanish.model.ICardsParser
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class CardsServiceImpl
    @Inject constructor(@Named("DBCardParser") private val cardsParser: ICardsParser) : ICardsService {

    //region Members
    private val allCards : Map<Long, CardTO>
    private val staticCards : List<CardTO>
    private var mainCards : List<CardTO>
    private var pressedCards : MutableList<CardTO>
    private var previousCardsStack : MutableList<List<CardTO>>
    //endregion

    init {
        allCards = cardsParser.getAllAvailableCards()
        staticCards = cardsParser.getStaticCards()
        mainCards = cardsParser.getInitialMainCards()
        pressedCards = mutableListOf()
        previousCardsStack = mutableListOf()
    }

    override fun getCard(cardId: Long): CardTO {
        allCards[cardId]?.let {
            return it
        }

        Log.e("CardsServiceImpl", "The card with the ID: $cardId doesn't exist")
        throw RuntimeException("Card not found")
    }

    override fun clear() {
        if (previousCardsStack.isNotEmpty()) {
            mainCards = previousCardsStack[0]
        }
        pressedCards.clear()
        previousCardsStack.clear()
    }

    override fun getStaticCards(): List<CardTO> {
        return this.staticCards
    }

    override fun getRegularCards(): List<CardTO> {
        return cardsParser.getRegularCards()
    }

    override fun getMainCards(): List<CardTO> {
        return this.mainCards
    }

    override fun getPressedCards(): List<CardTO> {
        return this.pressedCards
    }

    override fun updatePresentingCards(selectedCardId: Long) {
        val selectedCard = allCards[selectedCardId]
        selectedCard?.let {
            pressedCards.add(selectedCard)

            val newCardsIndicies = selectedCard.possibleChildren
            newCardsIndicies?.let {
                previousCardsStack.add(this.mainCards)
                this.mainCards = allCards.filter { newCardsIndicies.contains(it.key) }.values.toList()
            }
        }
    }

    override fun backOneLevel() {
        if (pressedCards.isNotEmpty()) {
            val removedCard = pressedCards.removeAt(pressedCards.size - 1)

            removedCard.possibleChildren?.let {
                this.mainCards = previousCardsStack.last()
                previousCardsStack.removeAt(previousCardsStack.size - 1)
            }
        }
    }
}