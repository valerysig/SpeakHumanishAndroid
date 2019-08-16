package com.valera.speakhumanish.model

import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

class CardsJSONParserImpl
@Inject constructor() : ICardsParser {

    companion object StaticValues {
        private const val STATIC_CARDS_ASSET_NAME = "staticCards.json"
        private const val MAIN_CARDS_ASSET_NAME = "mainCards.json"

        private val GSON = Gson()

        private fun loadJSONToObject(fileName: String): Map<Long, CardTO>? {
            val turnsType = object : TypeToken<Map<Long, CardTO>>() {}.type
            val jsonString = loadJSONFromAsset(fileName)

            val cardsMap = GSON.fromJson<Map<Long, CardTO>>(jsonString, turnsType)

            for ((key, card) in cardsMap) {
                card.id = key
            }

            return cardsMap
        }

        private fun loadJSONFromAsset(fileName: String): String? {
            val fileStream = Supplier.contextActivity.assets.open(fileName)
            fileStream.use {
                return try {
                    val size = fileStream.available()
                    val buffer = ByteArray(size)
                    fileStream.read(buffer)
                    String(buffer)
                } catch (ex: IOException) {
                    Log.e(CardsJSONParserImpl::class.java.name, "Error parsing the JSON file: $fileName", ex)
                    null
                }
            }
        }
    }

    private val staticCards: Map<Long, CardTO>
    private val allCards: Map<Long, CardTO>

    init {
        staticCards = loadJSONToObject(STATIC_CARDS_ASSET_NAME) ?: HashMap()
        val mainCards = loadJSONToObject(MAIN_CARDS_ASSET_NAME) ?: HashMap()

        val mutableCardsMap: MutableMap<Long, CardTO> = HashMap()
        mutableCardsMap.putAll(staticCards)
        mutableCardsMap.putAll(mainCards)

        allCards = mutableCardsMap
    }

    override fun getStaticCards(): List<CardTO> {
        return ArrayList(staticCards.values)
    }

    override fun getInitialMainCards(): List<CardTO> {
        val initialCards: List<CardTO> = allCards.values.filter { it.isInitialCard }.toList()
        if (initialCards.isEmpty()) {
            Log.e(CardsJSONParserImpl::class.java.name, "There were no initial cards")
        }
        return initialCards
    }

    override fun getAllAvailableCards(): Map<Long, CardTO> {
        return allCards
    }
    //endregion
}