package com.valera.speakhumanish.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.valera.speakhumanish.R
import com.valera.speakhumanish.model.Card
import com.valera.speakhumanish.model.Supplier
import com.valera.speakhumanish.services.IGridUpdater

class MutatingCardsAdapter(private val context: Context, private val cards: List<Card>, private val gridUpdater: IGridUpdater) : CardsAdapter(context, cards) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CardsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        view.layoutParams.width = Supplier.cardsWidth
        return MutatingCardsViewHolder(context, view, gridUpdater)
    }
}