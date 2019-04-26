package com.valera.speakhumanish.adapters

import android.content.Context
import android.util.Log
import android.view.View
import com.valera.speakhumanish.activitties.MainActivity
import com.valera.speakhumanish.services.IGridUpdater

class MainCardsViewHolder(private val context: Context, itemView: View, private val gridUpdater: IGridUpdater) : CardsViewHolder(context, itemView) {
    override fun printLog() {
        Log.i(MainActivity::class.java.simpleName, "A main card was pressed: " + this.currentCard!!.label)
    }

    override fun onPressed() {
        super.onPressed()

        gridUpdater.updateGrid(this.currentCard!!.id)
    }
}