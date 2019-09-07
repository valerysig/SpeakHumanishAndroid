package com.valera.speakhumanish.activities

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.valera.speakhumanish.R
import com.valera.speakhumanish.common.CARD_TO_EDIT
import com.valera.speakhumanish.common.DaggerProductionComponent
import com.valera.speakhumanish.common.setImageSrc
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
        currentCardImage.setImageSrc(currentCard)
        currentCardTitle.setText(currentCard.label)

        //Init buttons
        okBtn.setOnClickListener { Thread {okBtnPressed()}.start() }
        editCurrentCardBtn.setOnClickListener { Thread {imageChangeBtnPressed()}.start() }
        playBtn.setOnClickListener { Thread {playBtnPressed()}.start() }
        addSoundBtn.setOnClickListener { Thread { addSoundBtnPressed() }.start() }
        deleteCardBtn.setOnClickListener { Thread { deleteSoundCardBtnPressed() }.start() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            val imageUrl = getImagePath(data)
            currentCardImage.setImageURI(Uri.parse(imageUrl))
            currentCard.imageUri = imageUrl
        }
    }

    private fun okBtnPressed() {
        currentCard.label = currentCardTitle.text.toString()
        //TODO: Update the card in the DB
    }

    private fun imageChangeBtnPressed() {
        val imageIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(imageIntent, 0)
    }

    private fun playBtnPressed() {

    }

    private fun addSoundBtnPressed() {

    }

    private fun deleteSoundCardBtnPressed() {

    }

    // region Private methods
    private fun getImagePath(data: Intent?) : String? {
        val targetUri = data?.data
        targetUri?.let {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            contentResolver.query(targetUri, proj, null, null, null)?.use {
                val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                it.moveToFirst()

                return it.getString(columnIndex) // /storage/emulated/0/Download/MIGAYbrewery_perfectpair.jpg
            }

            return targetUri.toString()
        }

        Log.e("EditCardActivity", "Could not load the image Uri")
        throw Resources.NotFoundException()
    }
    //endregion
}