package com.valera.speakhumanish.activitties

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.valera.speakhumanish.adapters.MainCardsAdapter
import com.valera.speakhumanish.adapters.StaticCardsAdapter
import com.valera.speakhumanish.model.Supplier
import com.valera.speakhumanish.R
import com.valera.speakhumanish.services.DaggerServicesComponent
import com.valera.speakhumanish.services.ICardsService
import com.valera.speakhumanish.services.IGridUpdater
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), IGridUpdater {

    @Inject
    lateinit var cardsService: ICardsService

    override fun onCreate(savedInstanceState: Bundle?) {
        // Android init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Injects all services
        val servicesComponent = DaggerServicesComponent.create()
        servicesComponent.inject(this)

        setupStaticCardsRecyclerView()
        setupMainCardsRecyclerView()
    }

    override fun updateGrid(cardId : Long) {
        // Have to run on UI thread since we update the UI here
        runOnUiThread {
            cardsService.updatePresentingCards(cardId)
            val adapter = MainCardsAdapter(this, cardsService.getMainCards(), this)
            mainCardsView.adapter = adapter
        }
    }

    //region Private Methods
    private fun setupStaticCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        staticCardsView.layoutManager = layoutManager
        staticCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = StaticCardsAdapter(this, cardsService.getStaticCards())
        staticCardsView.adapter = adapter
    }

    private fun setupMainCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainCardsView.layoutManager = layoutManager
        mainCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = MainCardsAdapter(this, cardsService.getMainCards(), this)
        mainCardsView.adapter = adapter
    }
    //endregion
}
