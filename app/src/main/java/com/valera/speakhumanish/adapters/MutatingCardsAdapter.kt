package com.valera.speakhumanish.adapters

import android.content.Context
import android.view.ViewGroup
import com.valera.speakhumanish.model.CardTO
import com.valera.speakhumanish.services.IGridUpdater

class MutatingCardsAdapter(private val context: Context, cards: List<CardTO>, private val gridUpdater: IGridUpdater, backgroudColor : String? = null) : CardsAdapter(context, cards, backgroudColor) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CardsViewHolder {
        val cardsViewHolder = super.onCreateViewHolder(parent, position)
        return MutatingCardsViewHolder(context, cardsViewHolder.itemView, gridUpdater)
    }
}