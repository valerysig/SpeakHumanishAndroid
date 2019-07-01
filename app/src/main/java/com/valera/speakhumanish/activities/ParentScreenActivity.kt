package com.valera.speakhumanish.activities

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.valera.speakhumanish.R

class ParentScreenActivity : AppCompatActivity() {
    private val staticCardsFragment = ParentScreenStaticCardsFragment()
    private val mainCardsFragment = ParentScreenMainCardsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Android init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_screen)

        // Init the fragments selection functionality
        val navigationView : BottomNavigationView = findViewById(R.id.parent_screen_navigation)
        navigationView.setOnNavigationItemSelectedListener { menuItem -> getNavListener(menuItem) }

        // Init the default fragment
        supportFragmentManager.beginTransaction().replace(R.id.parent_screen_fragment_container, staticCardsFragment)
            .commit()
    }

    private fun getNavListener(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_main_cards_editor -> {
                supportFragmentManager.beginTransaction().replace(R.id.parent_screen_fragment_container, mainCardsFragment)
                    .commit()
                return true
            }

            R.id.nav_static_cards_editor -> {
                supportFragmentManager.beginTransaction().replace(R.id.parent_screen_fragment_container, staticCardsFragment)
                    .commit()
                return true
            }
        }

        Log.e("ParentScreenActivity", "Something wrong with selected a fragment")
        return false
    }
}