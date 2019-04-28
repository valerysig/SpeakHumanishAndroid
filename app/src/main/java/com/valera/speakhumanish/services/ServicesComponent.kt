package com.valera.speakhumanish.services

import com.valera.speakhumanish.activitties.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component (modules = [ServicesProductionModule::class])
interface ServicesComponent {

    fun inject(mainActivity: MainActivity)
}