package com.valera.speakhumanish.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.valera.speakhumanish.Model.Card
import com.valera.speakhumanish.R

class StaticCardsAdapter (val context: Context, private val cards: List<Card>) : RecyclerView.Adapter<CardsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CardsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        return CardsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(parent: CardsViewHolder, position: Int) {
        val card = this.cards[position]
        parent.setData(card, position)
    }
}