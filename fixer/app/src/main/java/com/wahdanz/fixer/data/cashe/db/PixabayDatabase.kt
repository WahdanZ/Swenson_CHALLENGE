package com.wahdanz.fixer.data.cashe.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wahdanz.fixer.data.cashe.dao.CachedPixabayDao
import com.wahdanz.fixer.data.cashe.dto.CurrencyCache

@Database(entities = [CurrencyCache::class], version = 1, exportSchema = false)
abstract class PixabayDatabase : RoomDatabase() {
    abstract fun cachedPixabayDao(): CachedPixabayDao

    companion object {
        private var INSTANCE: PixabayDatabase? = null
        private val lock = Any()
        fun getInstance(context: Context): PixabayDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            PixabayDatabase::class.java, "foxerdatabase.db"
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                    return INSTANCE as PixabayDatabase
                }
            }
            return INSTANCE as PixabayDatabase
        }
    }
}