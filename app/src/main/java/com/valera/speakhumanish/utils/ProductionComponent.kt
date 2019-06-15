package com.valera.speakhumanish.utils

import com.valera.speakhumanish.activitties.ChildScreenActivity
import com.valera.speakhumanish.model.ModelProductionModule
import com.valera.speakhumanish.services.ServicesProductionModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ServicesProductionModule::class, ModelProductionModule::class])
interface ProductionComponent {
    fun inject(childScreenActivity: ChildScreenActivity)
}