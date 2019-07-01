package com.valera.speakhumanish.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class CardsJSONParserImpl
@Inject constructor() : ICardsParser {

    companion object StaticValues {
        const val CLASS_NAME = "CardsJSONParserImpl"
        const val STATIC_CARDS_ASSET_NAME = "staticCards.json"
        const val MAIN_CARDS_ASSET_NAME = "mainCards.json"
        val INITIAL_CARDS_INDICES = listOf(1L)
        private val GSON = Gson()

        private fun loadJSONToObject(fileName: String): Map<Long, Card>? {
            val turnsType = object : TypeToken<Map<Long, Card>>() {}.type
            val jsonString = loadJSONFromAsset(fileName)

            return GSON.fromJson<Map<Long, Card>>(jsonString, turnsType)
        }

        private fun loadJSONFromAsset(fileName: String): String? {
            return try {
                val fileStream = Supplier.childScreenActivity.assets.open(fileName)
                val size = fileStream.available()
                val buffer = ByteArray(size)
                fileStream.read(buffer)
                fileStream.close()
                String(buffer)
            } catch (ex: IOException) {
                Log.e(CardsJSONParserImpl::class.java.name, "Error parsing the JSON file: $fileName", ex)
                null
            }
        }
    }

    private val staticCards: Map<Long, Card>
    private val allCards: Map<Long, Card>

    init {
        staticCards = loadJSONToObject(STATIC_CARDS_ASSET_NAME) ?: HashMap()
        val mainCards = loadJSONToObject(MAIN_CARDS_ASSET_NAME) ?: HashMap()

        // TODO: change this to GSON deserialization step?
        for ((key, card) in staticCards) {
            card.imageLocationID = Supplier.childScreenActivity.resources.getIdentifier(
                card.imageLocation,
                "drawable",
                Supplier.childScreenActivity.packageName
            )
            card.soundLocationID =
                if (card.soundLocation.equals("")) null else Supplier.childScreenActivity.resources.getIdentifier(
                    card.soundLocation,
                    "raw",
                    Supplier.childScreenActivity.packageName
                )
            card.id = key
        }
        for ((key, card) in mainCards) {
            card.imageLocationID = Supplier.childScreenActivity.resources.getIdentifier(
                card.imageLocation,
                "drawable",
                Supplier.childScreenActivity.packageName
            )
            card.soundLocationID =
                if (card.soundLocation.equals("")) null else Supplier.childScreenActivity.resources.getIdentifier(
                    card.soundLocation,
                    "raw",
                    Supplier.childScreenActivity.packageName
                )
            card.id = key
        }

        val mutableCardsMap: MutableMap<Long, Card> = HashMap()
        mutableCardsMap.putAll(staticCards)
        mutableCardsMap.putAll(mainCards)

        allCards = mutableCardsMap
    }

    override fun getStaticCards(): List<Card> {
        return ArrayList(staticCards.values)
    }

    override fun getInitialMainCards(): List<Card> {
        val initialCards: List<Card> = INITIAL_CARDS_INDICES.mapNotNull { allCards[it] }
        if (initialCards.isEmpty()) {
            Log.e(CardsJSONParserImpl::class.java.name, "There was no initiating card")
        }
        return initialCards
    }

    override fun getAllAvailableCards(): Map<Long, Card> {
        return allCards
    }

    // resources.getIdentifier("ball", "drawable", packageName)
    //endregion
}