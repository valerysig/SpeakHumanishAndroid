package com.valera.speakhumanish.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.valera.speakhumanish.Activitties.MainActivity
import com.valera.speakhumanish.Model.Card
import kotlinx.android.synthetic.main.card_item.view.*

open class CardsViewHolder(itemView: View) :  RecyclerView.ViewHolder(itemView) {
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

    protected open fun onPressed() {
        //TODO: set the card switching
        Log.i(MainActivity::class.java.simpleName, this.currentCard!!.label)
    }
}