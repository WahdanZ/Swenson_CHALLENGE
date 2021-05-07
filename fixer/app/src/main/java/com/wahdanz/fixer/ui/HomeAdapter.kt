package com.wahdanz.fixer.ui

import android.view.View
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.entity.FixerEntity
import com.wahdanz.fixer.extensions.RecyclerAdapter

class HomeAdapter(
    itemList: List<CurrencyEntity>,
    layoutResIds: Array<Int>,
    bindHolder: View.(CurrencyEntity) -> Unit,
    itemClick: CurrencyEntity.() -> Unit = {}
) : RecyclerAdapter<CurrencyEntity>(itemList, layoutResIds, bindHolder, itemClick)