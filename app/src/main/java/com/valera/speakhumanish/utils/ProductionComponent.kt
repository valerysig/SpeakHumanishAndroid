package com.valera.speakhumanish.utils

import com.valera.speakhumanish.activitties.MainActivity
import com.valera.speakhumanish.model.ModelProductionModule
import com.valera.speakhumanish.services.ServicesProductionModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ServicesProductionModule::class, ModelProductionModule::class])
interface ProductionComponent {
    fun inject(mainActivity: MainActivity)
}