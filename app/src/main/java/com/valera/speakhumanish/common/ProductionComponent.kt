package com.valera.speakhumanish.common

import com.valera.speakhumanish.activities.ChildScreenActivity
import com.valera.speakhumanish.activities.ParentScreenMainCardsFragment
import com.valera.speakhumanish.model.ModelProductionModule
import com.valera.speakhumanish.services.ServicesProductionModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ServicesProductionModule::class, ModelProductionModule::class])
interface ProductionComponent {
    fun inject(childScreenActivity: ChildScreenActivity)

    fun inject(parentScreenActivity: ParentScreenMainCardsFragment)
}