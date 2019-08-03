package com.valera.speakhumanish.model

import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class ModelProductionModule {
    @Binds
    @Named("JSONCardParser")
    abstract fun bindsJSONCardsParser(cardsParser : CardsJSONParserImpl) : ICardsParser

    @Binds
    @Named("DBCardParser")
    abstract fun bindsDBCardsParser(cardsParser: CardsDBParserImpl) : ICardsParser
}