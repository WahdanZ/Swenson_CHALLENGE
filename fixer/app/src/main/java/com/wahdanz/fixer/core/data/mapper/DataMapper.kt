package com.wahdanz.fixer.core.data.mapper

import com.wahdanz.fixer.core.domain.Entity

interface DataMapper<E : Entity, D : DataModel> {

    fun mapFromEntity(type: E): D

    fun mapToEntity(type: D): E
}