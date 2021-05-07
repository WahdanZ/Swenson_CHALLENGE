package com.wahdanz.fixer.domain.interactors

import com.wahdanz.fixer.core.domain.UseCase
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.repository.FixerRepository

class GetCurrencyUseCase(private val fixerRepository: FixerRepository) :
    UseCase<GetCurrencyUseCase.Params, CurrencyEntity> {

    data class Params(val symbol:String)

    override suspend fun execute(param: Params?): CurrencyEntity =
        fixerRepository.getCurrency(param!!.symbol)
}