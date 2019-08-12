package com.valera.speakhumanish.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valera.speakhumanish.model.Card

@Database(entities = [Card::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object StaticValues {
        private var INSTANCE : AppDatabase? = null

        fun getFileDatabase(context: Context) : AppDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "speak-humanish-DB").build()
            }

            return INSTANCE!!
        }

        fun getInMemoryDatabase(context: Context) : AppDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()
            }

            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

    abstract fun cardsModel() : CardDao
}