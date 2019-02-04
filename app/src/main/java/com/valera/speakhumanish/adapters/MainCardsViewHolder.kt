package com.valera.speakhumanish.adapters

import android.util.Log
import android.view.View
import com.valera.speakhumanish.activitties.MainActivity

class MainCardsViewHolder(itemView: View) : CardsViewHolder(itemView) {
    override fun onPressed() {
        Log.i(MainActivity::class.java.simpleName, "A main card was pressed: " + this.currentCard!!.label)
    }

}