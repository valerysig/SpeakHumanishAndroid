package com.valera.speakhumanish.services

import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class ServicesProductionModule {

    @Binds
    @Named("childCardService")
    abstract fun bindChildCardsServices(cardsService : CardsServiceImpl) : ICardsService

    @Binds
    @Named("parentCardService")
    abstract fun bindParentCardsServices(cardsService : CardsServiceImpl) : ICardsService

}