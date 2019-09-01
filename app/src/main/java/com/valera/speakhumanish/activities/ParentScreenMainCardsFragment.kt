package com.valera.speakhumanish.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.valera.speakhumanish.R
import com.valera.speakhumanish.adapters.MutatingCardsAdapter
import com.valera.speakhumanish.common.CARD_TO_EDIT
import com.valera.speakhumanish.common.DaggerProductionComponent
import com.valera.speakhumanish.model.Card
import com.valera.speakhumanish.model.CardTO
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
    private val COLUMNS_COUNT = 4

    //region Members
    @Inject
    @field:Named("parentCardService")
    lateinit var cardsService: ICardsService
    //endregion

    //region Constructors
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Fragment Init
        return inflater.inflate(R.layout.fragment_parent_screen_main_cards, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Injects all services
        val servicesComponent = DaggerProductionComponent.create()
        servicesComponent.inject(this)

        //Init the recycler view
        val layoutManager = GridLayoutManager(activity, COLUMNS_COUNT)
        layoutManager.orientation = androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
        nextCards.layoutManager = layoutManager

        //Init the buttons
        parentScreenBackBtn.setOnClickListener { Thread { backBtnPressed() }.start() }
        parentScreenClearAllBtn.setOnClickListener { Thread {clearAllBtnPressed() }.start() }
        addNewCardBtn.setOnClickListener { Thread {addNewCardBtnPressed() }.start() }
    }

    override fun onStart() {
        super.onStart()

        updateUI()
    }

    //endregion

    //region Methods
    override fun updateGrid(cardId: Long, itemView: View) {
        val cardToEdit = cardsService.getCard(cardId)
        startEditCardActivity(cardToEdit)
    }
    //endregion

    //region Private Methods
    private fun backBtnPressed() {

    }

    private fun clearAllBtnPressed() {

    }

    private fun addNewCardBtnPressed() {
        val cardToEdit = CardTO(Card(label = "What you want to say", imageLocation = "plus_button", isInitialCard = false))
        startEditCardActivity(cardToEdit)
    }

    private fun updateUI() {
        activity?.runOnUiThread {
            currentParentImage.setImageResource(INITAL_IMAGE)
            currentParentText.text = INIAL_TEXT

            val adapter = MutatingCardsAdapter(activity!!, cardsService.getRegularCards(), this)
            nextCards.adapter = adapter
        }
    }

    private fun startEditCardActivity(cardToEdit : CardTO) {
        val editIntent = Intent(activity, EditCardActivity::class.java)
        editIntent.putExtra(CARD_TO_EDIT, cardToEdit)
        startActivity(editIntent)
    }
    //endregion
}