package com.valera.speakhumanish.adapters

import android.content.Context
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.valera.speakhumanish.activitties.MainActivity
import com.valera.speakhumanish.model.Card
import kotlinx.android.synthetic.main.card_item.view.*

open class CardsViewHolder(private val context: Context, itemView: View) :  RecyclerView.ViewHolder(itemView) {
    var currentCard : Card? = null
    private var pos: Int = 0

    init {
        itemView.setOnClickListener {
            Thread {
                onPressed()
            }.start()
        }
    }

    fun setData(card: Card?, position: Int) {
        card?.let {
            this.itemView.imgShare.setImageResource(card.imageLocation)
            this.itemView.txvTitle.text = card.label
        }

        this.currentCard = card
        this.pos = position
    }

    private fun onPressed() {
        //TODO: set the card switching
        playSound()
        printLog()
    }

    private fun playSound() {
        if (currentCard != null && currentCard!!.soundLocation != null) {
            val soundPlayer = MediaPlayer.create(context, currentCard!!.soundLocation!!)
            soundPlayer.start()
        }
    }

    protected open fun printLog() {
        Log.i(MainActivity::class.java.simpleName, this.currentCard!!.label)
    }
}