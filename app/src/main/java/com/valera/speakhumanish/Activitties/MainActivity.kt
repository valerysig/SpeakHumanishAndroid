package com.valera.speakhumanish.Activitties

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.valera.speakhumanish.Adapters.StaticCardsAdapter
import com.valera.speakhumanish.Model.Supplier
import com.valera.speakhumanish.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        staticCardsView.layoutManager = layoutManager

        val adapter = StaticCardsAdapter(this, Supplier.StaticCards)
        staticCardsView.adapter = adapter
    }
}
