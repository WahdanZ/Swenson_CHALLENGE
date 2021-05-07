package com.wahdanz.fixer.core.cache.mapper

import com.wahdanz.fixer.core.data.mapper.DataModel

interface CacheMapper<C : CacheModel, M : DataModel> {
    fun mapFromCached(type: C?): M
    fun mapToCached(type: M?): C
}