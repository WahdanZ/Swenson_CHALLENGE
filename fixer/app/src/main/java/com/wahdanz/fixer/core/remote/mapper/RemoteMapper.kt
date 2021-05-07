package com.wahdanz.fixer.core.remote.mapper

import com.wahdanz.fixer.core.data.mapper.DataModel

interface RemoteMapper<in R : RemoteModel, out M : DataModel> {

    fun mapFromRemote(type: R): M
}