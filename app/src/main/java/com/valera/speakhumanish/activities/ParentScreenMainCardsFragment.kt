package com.valera.speakhumanish.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.valera.speakhumanish.R
import com.valera.speakhumanish.services.ICardsService
import javax.inject.Inject

class ParentScreenMainCardsFragment : Fragment() {

    @Inject
    lateinit var cardsService: ICardsService

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_parent_screen_main_cards, container, false)
    }
}