package com.valera.speakhumanish.activitties

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.valera.speakhumanish.adapters.MutatingCardsAdapter
import com.valera.speakhumanish.adapters.CardsAdapter
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

        // Setup the cards layouts
        setupStaticCardsRecyclerView()
        setupMainCardsRecyclerView()
        setupPressedCardsRecyclerView()

        // Hook up the buttons
        clearOneButton.setOnClickListener { Thread { clearOneButtonPressed() }.start() }
        clearAllButton.setOnClickListener { Thread { clearAllButtonPressed() }.start() }
    }

    override fun updateGrid(cardId : Long) {
        cardsService.updatePresentingCards(cardId)

        updateUI()
    }

    //region Private Methods
    private fun setupStaticCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        staticCardsView.layoutManager = layoutManager
        staticCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = MutatingCardsAdapter(this, cardsService.getStaticCards(), this)
        staticCardsView.adapter = adapter
    }

    private fun setupMainCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainCardsView.layoutManager = layoutManager
        mainCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = MutatingCardsAdapter(this, cardsService.getMainCards(), this)
        mainCardsView.adapter = adapter
    }

    private fun setupPressedCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        selectedCardsView.layoutManager = layoutManager
        selectedCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL

        val adapter = CardsAdapter(this, cardsService.getPressedCards())
        selectedCardsView.adapter = adapter
    }

    private fun updateUI() {
        // Have to run on UI thread since we update the UI here
        runOnUiThread {
            val mainCardsAdapter = MutatingCardsAdapter(this, cardsService.getMainCards(), this)
            mainCardsView.adapter = mainCardsAdapter

            val pressedCardsAdapter = CardsAdapter(this, cardsService.getPressedCards())
            selectedCardsView.adapter = pressedCardsAdapter
        }
    }

    private fun clearOneButtonPressed() {
        cardsService.backOneLevel()
        updateUI()
    }

    private fun clearAllButtonPressed() {
        cardsService.clear()
        updateUI()
    }
    //endregion
}
