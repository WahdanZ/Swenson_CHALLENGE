package com.wahdanz.fixer.data.mapper

import com.wahdanz.fixer.core.data.mapper.DataMapper
import com.wahdanz.fixer.data.model.CurrencyModel
import com.wahdanz.fixer.data.model.FixerModel
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.entity.FixerEntity

class CurrencyDataMapper :
    DataMapper<CurrencyEntity, CurrencyModel> {
    override fun mapFromEntity(type: CurrencyEntity): CurrencyModel {
        return CurrencyModel(
            type.currency,
            type.value,
            type.date
        )
    }

    override fun mapToEntity(type: CurrencyModel): CurrencyEntity {
        return CurrencyEntity(
            type.currency,
            type.value,
            type.date
        )
    }
}