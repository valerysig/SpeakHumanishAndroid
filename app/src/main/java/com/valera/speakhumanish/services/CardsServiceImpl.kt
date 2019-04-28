package com.valera.speakhumanish.services

import com.valera.speakhumanish.model.Card
import com.valera.speakhumanish.model.ICardsParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardsServiceImpl
    @Inject constructor(private val cardsParser: ICardsParser) : ICardsService {

    //region Members
    private val allCards : Map<Long, Card>
    private val staticCards : List<Card>
    private var mainCards : List<Card>
    private var pressedCards : MutableList<Card>
    private var previousCardsStack : MutableList<List<Card>>
    //endregion

    init {
        //TODO: After the JSON parsing class is done remove the following 3 lines
        allCards = cardsParser.getAllAvailableCards()
        staticCards = cardsParser.getStaticCards()
        mainCards = cardsParser.getStartingMainCards()
        pressedCards = mutableListOf()
        previousCardsStack = mutableListOf()
    }

    override fun clear() {
        if (previousCardsStack.isNotEmpty()) {
            mainCards = previousCardsStack[0]
        }
        pressedCards.clear()
        previousCardsStack.clear()
    }

    override fun getStaticCards(): List<Card> {
        return this.staticCards
    }

    override fun getMainCards(): List<Card> {
        return this.mainCards
    }

    override fun getPressedCards(): List<Card> {
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