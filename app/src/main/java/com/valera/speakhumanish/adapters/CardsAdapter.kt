package com.valera.speakhumanish.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.valera.speakhumanish.R
import com.valera.speakhumanish.model.CardTO
import com.valera.speakhumanish.model.Supplier

open class CardsAdapter (private val context: Context, private val cards: List<CardTO>, private val backgroudColor : String? = null) : androidx.recyclerview.widget.RecyclerView.Adapter<CardsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CardsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false)
        view.layoutParams.width = Supplier.cardsWidth
        backgroudColor?.let {
            view.setBackgroundColor(Color.parseColor(backgroudColor))
        }
        return CardsViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    override fun onBindViewHolder(parent: CardsViewHolder, position: Int) {
        val card = this.cards[position]
        parent.setData(card, position)
    }
}