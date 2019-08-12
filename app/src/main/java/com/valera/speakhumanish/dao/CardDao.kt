package com.valera.speakhumanish.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.valera.speakhumanish.model.Card

@Dao
interface CardDao {
    @Query("select * from Cards")
    fun getAllCards() : List<Card>

    @Query("select * from Cards where id = :id")
    fun getCardById(id : Long) : List<Card>

    @Insert(onConflict = REPLACE)
    fun insertCard(card : Card)

    @Insert(onConflict = REPLACE)
    fun insertCard(cards : List<Card>)

    @Delete
    fun deleteCard(card : Card)
}