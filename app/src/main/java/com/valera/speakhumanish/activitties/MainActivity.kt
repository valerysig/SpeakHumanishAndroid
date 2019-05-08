package com.valera.speakhumanish.activitties

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.valera.speakhumanish.R
import com.valera.speakhumanish.adapters.CardsAdapter
import com.valera.speakhumanish.adapters.MutatingCardsAdapter
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
        updateUI()

        // Hook up the buttons
        clearOneButton.setOnClickListener { Thread { clearOneButtonPressed() }.start() }
        clearAllButton.setOnClickListener { Thread { clearAllButtonPressed() }.start() }
    }

    override fun updateGrid(cardId : Long, itemView : View) {
        cardsService.updatePresentingCards(cardId)

        // ((itemView as CardView).parent as View).y

        runOnUiThread {
            val moveDown = ObjectAnimator.ofFloat(itemView, "translationY", getYPoints(itemView))
            val moveLeft = ObjectAnimator.ofFloat(itemView, "translationX", getXPoints(itemView))

            val animator = AnimatorSet()
            animator.play(moveDown).with(moveLeft)
            animator.duration = 1000

            animator.addListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {
                    // Do nothing
                }

                override fun onAnimationEnd(animation: Animator?) {
                    itemView.elevation -= 2
                    updateUI()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    // Do nothing
                }

                override fun onAnimationStart(animation: Animator?) {
                    // Bring the recycler views to front
                    (itemView.parent as RecyclerView).bringToFront()

                    // Bring the cards to front
                    itemView.elevation += 2
                    itemView.bringToFront()

                }
            })

            animator.start()
        }
    }

    //region Private Methods
    private fun setupStaticCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        staticCardsView.layoutManager = layoutManager
        staticCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    private fun setupMainCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mainCardsView.layoutManager = layoutManager
        mainCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    private fun setupPressedCardsRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        selectedCardsView.layoutManager = layoutManager
        selectedCardsView.layoutDirection = View.LAYOUT_DIRECTION_RTL
    }

    private fun updateUI() {
        // Have to run on UI thread since we update the UI here
        runOnUiThread {
            val adapter = MutatingCardsAdapter(this, cardsService.getStaticCards(), this)
            staticCardsView.adapter = adapter

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

    private fun getXPoints(itemView : View) : Float {
        val cardsInPlayBar = cardsService.getPressedCards().size
        return - (itemView.width + (itemView as LinearLayout).paddingBottom*2) * cardsInPlayBar + (mainActivityView.width - itemView.x)
    }

    private fun getYPoints(itemView : View) : Float {
        return playBarView.y - ((itemView as LinearLayout).parent as View).y - itemView.y + itemView.paddingBottom
    }
    //endregion
}
