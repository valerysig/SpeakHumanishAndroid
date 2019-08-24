package com.valera.speakhumanish.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valera.speakhumanish.R
import com.valera.speakhumanish.adapters.MutatingCardsAdapter
import com.valera.speakhumanish.common.DaggerProductionComponent
import com.valera.speakhumanish.model.Supplier
import com.valera.speakhumanish.services.ICardsService
import com.valera.speakhumanish.services.IGridUpdater
import kotlinx.android.synthetic.main.fragment_parent_screen_main_cards.*
import javax.inject.Inject
import javax.inject.Named

class ParentScreenMainCardsFragment : Fragment(), IGridUpdater {

    private val INITAL_IMAGE = Supplier.contextActivity.resources.getIdentifier(
        "home_image", "drawable", Supplier.contextActivity.packageName
    )
    private val INIAL_TEXT = "Home screen"

    //region Members
    @Inject
    @field:Named("parentCardService")
    lateinit var cardsService: ICardsService
    //endregion

    //region Constructors
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Injects all services
        val servicesComponent = DaggerProductionComponent.create()
        servicesComponent.inject(this)

        // Fragment Init
        return inflater.inflate(R.layout.fragment_parent_screen_main_cards, container, false)
    }

    override fun onStart() {
        super.onStart()

        //Init the recycler view
        val layoutManager = GridLayoutManager(activity, 6)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        nextCards.layoutManager = layoutManager

        //Init the buttons
        parentScreenBackBtn.setOnClickListener { Thread { backBtnPressed() }.start() }
        parentScreenClearAllBtn.setOnClickListener { Thread {clearAllBtnPressed() }.start() }

        updateUI()
    }

    //endregion

    //region Methods
    override fun updateGrid(cardId: Long, itemView: View) {
        cardsService.updatePresentingCards(cardId)

        updateUI()
    }
    //endregion

    //region Private Methods
    private fun backBtnPressed() {
        cardsService.backOneLevel()

        updateUI()
    }

    private fun clearAllBtnPressed() {
        cardsService.clear()

        updateUI()
    }

    private fun updateUI() {
        activity?.runOnUiThread {
            val currentCard = cardsService.lastPressedCard
            if (currentCard == null) {
                currentParentImage.setImageResource(INITAL_IMAGE)
                currentParentText.text = INIAL_TEXT
            } else {
                // Setting the current image on the screen
                currentParentImage.setImageResource(currentCard.imageLocationID)
                currentParentText.text = currentCard.label
            }


            val adapter = MutatingCardsAdapter(activity!!, cardsService.getMainCards(), this)
            nextCards.adapter = adapter
        }
    }
    //endregion
}