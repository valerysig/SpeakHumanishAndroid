package com.valera.speakhumanish.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import com.valera.speakhumanish.model.Card

@Dao
interface CardDao {
    @Query("select * from Cards")
    fun getAllCards() : List<Card>

    @Query("select * from Cards where possible_children is null")
    fun getStaticCards() : List<Card>

    @Query("select * from Cards where id = :id")
    fun getCardById(id : Long) : List<Card>

    @Insert(onConflict = IGNORE)
    fun insertCard(card : Card)

    @Insert(onConflict = IGNORE)
    fun insertCard(cards : List<Card>)

    @Update
    fun updateCard(card : Card)

    @Delete
    fun deleteCard(card : Card)
}