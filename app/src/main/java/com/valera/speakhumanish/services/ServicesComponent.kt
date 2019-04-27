package com.valera.speakhumanish.services

import com.valera.speakhumanish.activitties.MainActivity
import dagger.Component

@Component (modules = [ServicesProductionModule::class])
interface ServicesComponent {

    fun inject(mainActivity: MainActivity)
}