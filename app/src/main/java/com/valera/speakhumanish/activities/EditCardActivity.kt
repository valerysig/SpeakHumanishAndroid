package com.valera.speakhumanish.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.valera.speakhumanish.R
import com.valera.speakhumanish.common.CARD_TO_EDIT
import com.valera.speakhumanish.common.DaggerProductionComponent
import com.valera.speakhumanish.model.CardTO
import kotlinx.android.synthetic.main.activity_edit_card.*

class EditCardActivity : Activity() {

    private lateinit var currentCard : CardTO

    override fun onCreate(savedInstanceState: Bundle?) {
        //Android init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_card)

        // Injects all services
        val servicesComponent = DaggerProductionComponent.create()
        servicesComponent.inject(this)

        // Init the current card
        val card = intent.extras[CARD_TO_EDIT]
        if (card is CardTO) {
            currentCard = card
        } else {
            Log.e("EditCardActivity", "A wrong object was inserted into this activity: $card")
            throw IllegalArgumentException("A CardTO was expected but ${card.javaClass} received")
        }

        //Init the views
        currentCardImage.setImageResource(card.imageLocationID)
        currentCardTitle.setText(card.label)

        //Init buttons
        okBtn.setOnClickListener { Thread {okBtnPressed()}.start() }
        editCurrentCardBtn.setOnClickListener { Thread {imageChangeBtnPressed()}.start() }
        playBtn.setOnClickListener { Thread {playBtnPressed()}.start() }
        addSoundBtn.setOnClickListener { Thread { addSoundBtnPressed() }.start() }
        deleteCardBtn.setOnClickListener { Thread { deleteSoundCardBtnPressed() }.start() }
    }

    private fun okBtnPressed() {

    }

    private fun imageChangeBtnPressed() {

    }

    private fun playBtnPressed() {

    }

    private fun addSoundBtnPressed() {

    }

    private fun deleteSoundCardBtnPressed() {

    }
}