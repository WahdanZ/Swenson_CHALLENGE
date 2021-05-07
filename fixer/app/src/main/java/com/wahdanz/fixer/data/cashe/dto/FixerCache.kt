package com.wahdanz.fixer.data.cashe.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wahdanz.fixer.core.cache.mapper.CacheModel

data class FixerCache(
    @PrimaryKey
    val date: String,
    val listCurrencies: List<CurrencyCache> = listOf()

) : CacheModel

@Entity(
    tableName = "currency_table",
    primaryKeys= [ "currency", "date" ]
)
data class CurrencyCache(
    val currency: String,
    val value: Double,
//    @ForeignKey(
//        entity = FixerCache::class,
//        parentColumns = ["date"],
//        childColumns = ["date"],
//        onDelete = CASCADE
//    )
    val date: String
) : CacheModel
