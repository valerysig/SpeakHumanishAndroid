package com.valera.speakhumanish.adapters

import android.content.Context
import android.util.Log
import android.view.View
import com.valera.speakhumanish.activitties.MainActivity

class MainCardsViewHolder(private val context: Context, itemView: View) : CardsViewHolder(context, itemView) {
    override fun printLog() {
        Log.i(MainActivity::class.java.simpleName, "A main card was pressed: " + this.currentCard!!.label)
    }

}