package com.valera.speakhumanish.services

import android.view.View

interface IGridUpdater {
    fun updateGrid(cardId : Long, itemView : View)
}