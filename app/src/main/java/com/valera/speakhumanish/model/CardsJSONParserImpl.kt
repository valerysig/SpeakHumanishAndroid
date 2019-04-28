package com.valera.speakhumanish.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CardsJSONParserImpl
    @Inject constructor()
    : ICardsParser {

    private var staticCards : Map<Long, Card>
    private var allCards : Map<Long, Card>

    init {
        staticCards = loadJSONToObject("staticCards.json") ?: HashMap()
        val mainCards = loadJSONToObject("mainCards.json") ?: HashMap()

        for (key in staticCards.keys) {
            val card = staticCards[key]!!
            card.imageLocationID = Supplier.mainActivity.resources.getIdentifier(card.imageLocationStr, "drawable", Supplier.mainActivity.packageName)
            card.soundLocationID = if (card.soundLocationStr.equals(""))  null else Supplier.mainActivity.resources.getIdentifier(card.soundLocationStr, "raw", Supplier.mainActivity.packageName)
            card.id = key
        }
        for (key in mainCards.keys) {
            val card = mainCards[key]!!
            card.imageLocationID = Supplier.mainActivity.resources.getIdentifier(card.imageLocationStr, "drawable", Supplier.mainActivity.packageName)
            card.soundLocationID = if (card.soundLocationStr.equals(""))  null else Supplier.mainActivity.resources.getIdentifier(card.soundLocationStr, "raw", Supplier.mainActivity.packageName)
            card.id = key
        }

        val mutableCardsMap : MutableMap<Long, Card> = HashMap()
        mutableCardsMap.putAll(staticCards)
        mutableCardsMap.putAll(mainCards)

        allCards = mutableCardsMap
    }

    override fun getStaticCards(): List<Card> {
        return ArrayList(staticCards.values)
    }

    override fun getStartingMainCards(): List<Card> {
        val startingCards : MutableList<Card> = LinkedList()
        STARTING_CARDS_INDICIES.forEach {
            allCards[it.toLong()]?.let {currentCard ->
                startingCards.add(currentCard)
            }
        }

        if (startingCards.isEmpty()) {
            Log.e(CLASS_NAME, "There was no starting card")
        }

        return startingCards
    }

    override fun getAllAvailableCards(): Map<Long, Card> {
        return allCards
    }

    //region Private Methods
    private fun loadJSONToObject(fileName: String) : Map<Long, Card>? {
        val turnsType = object : TypeToken<Map<String,Map<Long, Card>>>() {}.type
        val jsonString = loadJSONFromAsset(fileName)

        val jsonObject = Gson().fromJson<Map<String,Map<Long, Card>>>(jsonString, turnsType)

        return jsonObject["cards"]
    }

    private fun loadJSONFromAsset(fileName : String): String? {
        var json: String?
        try {
            val fileStream = Supplier.mainActivity.assets.open(fileName)
            val size = fileStream.available()
            val buffer = ByteArray(size)
            fileStream.read(buffer)
            fileStream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            Log.e(CLASS_NAME, "Error parsing the JSON file: $fileName")
            return null
        }

        return json
    }

    companion object StaticValues {
        const val CLASS_NAME = "CardsJSONParserImpl"
        val STARTING_CARDS_INDICIES = listOf(1)
    }

    // resources.getIdentifier("ball", "drawable", packageName)
    //endregion
}