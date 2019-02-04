package com.valera.speakhumanish.Activitties

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.valera.speakhumanish.Adapters.MainCardsAdapter
import com.valera.speakhumanish.Adapters.StaticCardsAdapter
import com.valera.speakhumanish.Model.Supplier
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

        val adapter = StaticCardsAdapter(this, Supplier.StaticCards)
        staticCardsView.adapter = adapter
    }

    private fun setupMainCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mainCardsView.layoutManager = layoutManager

        val adapter = MainCardsAdapter(this, Supplier.mainCards)
        mainCardsView.adapter = adapter
    }
}
