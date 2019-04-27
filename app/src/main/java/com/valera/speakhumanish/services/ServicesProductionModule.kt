package com.valera.speakhumanish.services

import dagger.Binds
import dagger.Module

@Module
abstract class ServicesProductionModule {

    @Binds
    abstract fun bindCardsServices(cardsService : CardsServiceImpl) : ICardsService
}