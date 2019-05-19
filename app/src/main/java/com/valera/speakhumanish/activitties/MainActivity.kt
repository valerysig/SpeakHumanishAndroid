package com.valera.speakhumanish.activitties

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.valera.speakhumanish.R
import com.valera.speakhumanish.adapters.CardsAdapter
import com.valera.speakhumanish.adapters.MutatingCardsAdapter
import com.valera.speakhumanish.model.Supplier
import com.valera.speakhumanish.services.ICardsService
import com.valera.speakhumanish.services.IGridUpdater
import com.valera.speakhumanish.utils.DaggerProductionComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : Activity(), IGridUpdater {

    @Inject
    lateinit var cardsService: ICardsService

    override fun onCreate(savedInstanceState: Bundle?) {
        // Android init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Supplier.mainActivity = this
        Supplier.cardsWidth = Resources.getSystem().displayMetrics.widthPixels / 8

        // Injects all services
        val servicesComponent = DaggerProductionComponent.create()
        servicesComponent.inject(this)

        // Setup the cards layouts
        setupCardsRecyclerView(staticCardsView, 1)
        setupCardsRecyclerView(mainCardsView, 2)
        setupCardsRecyclerView(selectedCardsView, 1)
        updateUI()

        // Hook up the buttons
        clearOneButton.setOnClickListener { Thread { clearOneButtonPressed() }.start() }
        clearAllButton.setOnClickListener { Thread { clearAllButtonPressed() }.start() }
        playAllButton.setOnClickListener { Thread { playAllButtonPressed() }.start() }
    }

    override fun updateGrid(cardId : Long, itemView : View) {
        cardsService.updatePresentingCards(cardId)
        val animator = getSetupAnimator(itemView)

        runOnUiThread {
            animator.start()
        }
    }

    //region Private Methods
    private fun setupCardsRecyclerView(view: RecyclerView, columnCount : Int, backgroudColor: String? = null) {
        val layoutManager = GridLayoutManager(this, columnCount)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        view.layoutManager = layoutManager
        view.layoutDirection = View.LAYOUT_DIRECTION_RTL
        backgroudColor?.let {
            view.setBackgroundColor(Color.parseColor(backgroudColor))
        }
    }

    private fun updateUI() {
        // Have to run on UI thread since we update the UI here
        runOnUiThread {
            val adapter = MutatingCardsAdapter(this, cardsService.getStaticCards(), this, "#7CFC00")
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

    private fun playAllButtonPressed() {
        cardsService.getPressedCards().forEach { card ->
            card.soundLocationID?.let {
                val soundPlayer = MediaPlayer.create(this, card.soundLocationID!!)
                soundPlayer.start()
            }
        }
    }

    private fun getSetupAnimator(itemView : View) : AnimatorSet {
        val animationDuration = 1000L

        val moveDown = ObjectAnimator.ofFloat(itemView, "translationY", getYPoints(itemView))
        val moveLeft = ObjectAnimator.ofFloat(itemView, "translationX", getXPoints(itemView))

        val animator = AnimatorSet()
        animator.play(moveDown).with(moveLeft)
        animator.duration = animationDuration

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

        return animator
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
