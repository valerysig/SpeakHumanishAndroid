package com.valera.speakhumanish.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class CardsJSONParserImpl
@Inject constructor() : ICardsParser {

    companion object StaticValues {
        const val CLASS_NAME = "CardsJSONParserImpl"
        const val STATIC_CARDS_ASSET_NAME = "staticCards.json"
        const val MAIN_CARDS_ASSET_NAME = "mainCards.json"
        val INITIAL_CARDS_INDICES = listOf(1L)
        private val GSON = Gson()

        private fun loadJSONToObject(fileName: String): Map<Long, CardTO>? {
            val turnsType = object : TypeToken<Map<Long, CardTO>>() {}.type
            val jsonString = loadJSONFromAsset(fileName)

            return GSON.fromJson<Map<Long, CardTO>>(jsonString, turnsType)
        }

        private fun loadJSONFromAsset(fileName: String): String? {
            return try {
                val fileStream = Supplier.contextActivity.assets.open(fileName)
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

    private val staticCards: Map<Long, CardTO>
    private val allCards: Map<Long, CardTO>

    init {
        staticCards = loadJSONToObject(STATIC_CARDS_ASSET_NAME) ?: HashMap()
        val mainCards = loadJSONToObject(MAIN_CARDS_ASSET_NAME) ?: HashMap()

        // TODO: change this to GSON deserialization step?
        for ((key, card) in staticCards) {
            card.id = key
        }
        for ((key, card) in mainCards) {
            card.id = key
        }

        val mutableCardsMap: MutableMap<Long, CardTO> = HashMap()
        mutableCardsMap.putAll(staticCards)
        mutableCardsMap.putAll(mainCards)

        allCards = mutableCardsMap
    }

    override fun getStaticCards(): List<CardTO> {
        return ArrayList(staticCards.values)
    }

    override fun getInitialMainCards(): List<CardTO> {
        val initialCards: List<CardTO> = INITIAL_CARDS_INDICES.mapNotNull { allCards[it] }
        if (initialCards.isEmpty()) {
            Log.e(CardsJSONParserImpl::class.java.name, "There was no initiating card")
        }
        return initialCards
    }

    override fun getAllAvailableCards(): Map<Long, CardTO> {
        return allCards
    }
    //endregion
}