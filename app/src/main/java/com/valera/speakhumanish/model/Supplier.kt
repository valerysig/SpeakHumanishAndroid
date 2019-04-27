package com.valera.speakhumanish.model

import com.valera.speakhumanish.R

//TODO: VALERA This will be irrelevant once we add a JSON parsing method
object Supplier {
    val StaticCards = listOf(
        Card(0, "Some text 1212121", R.drawable.ball),
        Card(0, "Some text 1212121", R.drawable.baloon),
        Card(0, "Some text 1212121", R.drawable.car),
        Card(0, "Some text 1212121", R.drawable.doll),
        Card(0, "Some text 1212121", R.drawable.enough)
    )

    var mainCards = listOf(
        Card(1, "אני רוצה",R.drawable.where_to_go, soundLocation =  R.raw.english_i_want, possibleChildren = setOf(2,3,4,5))
    )

    var allCards = listOf(
        Card(1, "אני רוצה",R.drawable.where_to_go, soundLocation =  R.raw.english_i_want, possibleChildren = setOf(2,3,4,5)),
        Card(2, "לשחק", R.drawable.to_watch_tv, possibleChildren = setOf(6, 7)),
        Card(3, "לקרוא ספר", R.drawable.one_more_time, possibleChildren = setOf(8, 9)),
        Card(4, "לאכול", R.drawable.people, possibleChildren = setOf(10, 11)),
        Card(5, "ללכת", R.drawable.to_eat, possibleChildren = setOf(12, 13)),
        Card(6, "כרורגל", R.drawable.to_play),
        Card(7, "פלייסטיישן", R.drawable.ball),
        Card(8, "אלכימאי", R.drawable.baloon),
        Card(9, "צופן דה וינצ'י", R.drawable.car),
        Card(10, "שוקולד", R.drawable.doll),
        Card(11, "תפוז", R.drawable.doll),
        Card(12, "לבקר את סבתא", R.drawable.doll),
        Card(13, "גן חיות", R.drawable.doll))
}