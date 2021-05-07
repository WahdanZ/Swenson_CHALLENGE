package com.wahdanz.fixer.domain.interactors

import com.wahdanz.fixer.core.domain.UseCase
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.entity.FixerEntity
import com.wahdanz.fixer.domain.repository.FixerRepository

class GetAllCurrenciesUseCase(private val fixerRepository: FixerRepository) :
    UseCase<GetAllCurrenciesUseCase.Params, List<CurrencyEntity>> {

    data class Params(val date: List<String>)

    override suspend fun execute(param: Params?): List<CurrencyEntity> =
        fixerRepository.getAllCurrencies(param!!.date)
}