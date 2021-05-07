package com.wahdanz.fixer.data.cashe.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahdanz.fixer.data.cashe.dto.CurrencyCache
import com.wahdanz.fixer.data.cashe.dto.FixerCache
import java.util.*

@Dao
interface CachedPixabayDao {
    @Query("SELECT * FROM currency_table ")
    suspend fun getAllCurrencies(): List<CurrencyCache>
    @Query("SELECT * FROM currency_table Where currency = :currency")
    suspend fun getCurrency(currency: String): CurrencyCache
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllCurrencies(fixer: List<CurrencyCache>)
}
