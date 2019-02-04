package com.valera.speakhumanish.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.valera.speakhumanish.Model.MainCard
import com.valera.speakhumanish.R

class MainCardsAdapter(private val context: Context, private val cards: List<MainCard>) : StaticCardsAdapter(context, cards) {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CardsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        return MainCardsViewHolder(view)
    }
}