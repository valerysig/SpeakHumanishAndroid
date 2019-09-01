package com.valera.speakhumanish.activities

import android.app.Activity
import android.os.Bundle
import com.valera.speakhumanish.R
import com.valera.speakhumanish.common.DaggerProductionComponent

class EditCardActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        //Android init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card)

        // Injects all services
        val servicesComponent = DaggerProductionComponent.create()
        servicesComponent.inject(this)
    }
}