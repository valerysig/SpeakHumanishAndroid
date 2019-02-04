package com.valera.speakhumanish.Model

import com.valera.speakhumanish.R

//TODO: VALERA This will be irrelevant once we add a JSON parsing method
object Supplier {
    val StaticCards = listOf(
        Card("Some text 1212121", R.drawable.ball),
        Card("Some text 1212121", R.drawable.baloon),
        Card("Some text 1212121", R.drawable.car),
        Card("Some text 1212121", R.drawable.doll),
        Card("Some text 1212121", R.drawable.enough)
    )

    val mainCards = listOf(
        MainCard(1, "אני רוצה",R.drawable.where_to_go, possibleChildren = listOf(2,3,4,5).toSet()),
        MainCard(2, "לשחק", R.drawable.to_watch_tv, possibleChildren = listOf(6, 7).toSet()),
        MainCard(3, "לקרוא ספר", R.drawable.one_more_time, possibleChildren = listOf(8, 9).toSet()),
        MainCard(4, "לאכול", R.drawable.people, possibleChildren = listOf(10, 11).toSet()),
        MainCard(5, "ללכת", R.drawable.to_eat, possibleChildren = listOf(12, 13).toSet()),
        MainCard(6, "כרורגל", R.drawable.to_play),
        MainCard(7, "פלייסטיישן", R.drawable.ball),
        MainCard(8, "אלכימאי", R.drawable.baloon),
        MainCard(9, "צופן דה וינצ'י", R.drawable.car),
        MainCard(10, "שוקולד", R.drawable.doll),
        MainCard(11, "תפוז", R.drawable.doll),
        MainCard(12, "לבקר את סבתא", R.drawable.doll),
        MainCard(13, "גן חיות", R.drawable.doll)

    )
}