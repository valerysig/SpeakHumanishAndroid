package com.valera.speakhumanish.model

import dagger.Binds
import dagger.Module

@Module
abstract class ModelProductionModule {
    @Binds
    abstract fun bindsCardsParser(cardsParser : CardsJSONParserImpl) : ICardsParser
}