package com.valera.speakhumanish.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valera.speakhumanish.R
import com.valera.speakhumanish.services.ICardsService
import com.valera.speakhumanish.services.IGridUpdater
import javax.inject.Inject
import javax.inject.Named

class ParentScreenMainCardsFragment : Fragment(), IGridUpdater {

    //region Members
    @Inject
    @field:Named("parentCardService")
    lateinit var cardsService: ICardsService
    //endregion

    //region Constructors
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_parent_screen_main_cards, container, false)
    }
    //endregion

    //region Methods
    override fun updateGrid(cardId: Long, itemView: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    //endregion
}