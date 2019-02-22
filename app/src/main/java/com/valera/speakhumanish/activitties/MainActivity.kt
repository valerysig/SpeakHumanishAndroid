package com.valera.speakhumanish.activitties

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.valera.speakhumanish.adapters.MainCardsAdapter
import com.valera.speakhumanish.adapters.StaticCardsAdapter
import com.valera.speakhumanish.model.Supplier
import com.valera.speakhumanish.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupStaticCardsRecyclerView()
        setupMainCardsRecyclerView()
    }


    private fun setupStaticCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        staticCardsView.layoutManager = layoutManager
        staticCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = StaticCardsAdapter(this, Supplier.StaticCards)
        staticCardsView.adapter = adapter
    }

    private fun setupMainCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainCardsView.layoutManager = layoutManager
        mainCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = MainCardsAdapter(this, Supplier.mainCards)
        mainCardsView.adapter = adapter
    }
}
