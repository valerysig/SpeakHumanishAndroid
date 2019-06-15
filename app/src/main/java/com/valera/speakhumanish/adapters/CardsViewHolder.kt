package com.valera.speakhumanish.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.valera.speakhumanish.activitties.ChildScreenActivity
import com.valera.speakhumanish.model.Card
import kotlinx.android.synthetic.main.card_item.view.*


open class CardsViewHolder(private val context: Context, itemView: View) :  RecyclerView.ViewHolder(itemView) {
    var currentCard : Card? = null
    private var pos: Int = 0

    init {
        itemView.setOnClickListener {
            Thread {
                val oldColorFilter = itemView.background.colorFilter

                itemView.background.setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP)
                itemView.postInvalidate()

                Thread.sleep(50)

                itemView.background.colorFilter = oldColorFilter
                itemView.postInvalidate()
                onPressed()
            }.start()
        }
    }

    fun setData(card: Card?, position: Int) {
        card?.let {
            this.itemView.imgShare.setImageResource(card.imageLocationID)
            this.itemView.txvTitle.text = card.label
        }

        this.currentCard = card
        this.pos = position
    }

    protected open fun onPressed() {
        playSound()
        printLog()
    }

    private fun playSound() {
        if (currentCard != null && currentCard!!.soundLocationID != null) {
            val soundPlayer = MediaPlayer.create(context, currentCard!!.soundLocationID!!)
            soundPlayer.start()
        }
    }

    protected open fun printLog() {
        Log.i(ChildScreenActivity::class.java.simpleName, this.currentCard!!.label)
    }
}