package com.valera.speakhumanish.services

import com.valera.speakhumanish.model.Card
import com.valera.speakhumanish.model.Supplier
import javax.inject.Inject

class CardsServiceImpl
    @Inject constructor() : ICardsService {

    //region Members
    private val allCards : Map<Long, Card>
    private val staticCards : List<Card>
    private var mainCards : List<Card>
    private var pressedCards : MutableList<Card>
    private var previousCardsStack : MutableList<List<Card>>
    //endregion

    init {
        //TODO: After the JSON parsing class is done remove the following 3 lines
        allCards = Supplier.allCards.map { it.id to it }.toMap()
        staticCards = Supplier.StaticCards
        mainCards = Supplier.mainCards
        pressedCards = mutableListOf()
        previousCardsStack = mutableListOf()
    }

    override fun getStaticCards(): List<Card> {
        return this.staticCards
    }

    override fun getMainCards(): List<Card> {
        return this.mainCards
    }

    override fun updatePresentingCards(selectedCard: Card) {
        pressedCards.add(selectedCard)
        previousCardsStack.add(this.mainCards)

        val newCardsIndicies = selectedCard.possibleChildren
        this.mainCards = allCards.filter { newCardsIndicies.contains(it.key) }.values.toList()
    }

    override fun backOneLevel() {
        if (previousCardsStack.isNotEmpty()) {
            this.mainCards = previousCardsStack.last()
            previousCardsStack.dropLast(1)

            pressedCards.dropLast(1)
        }
    }
}