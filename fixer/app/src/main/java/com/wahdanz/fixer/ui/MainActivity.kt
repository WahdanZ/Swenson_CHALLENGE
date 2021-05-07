package com.wahdanz.fixer.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.blongho.country_data.World
import com.wahdanz.fixer.R
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.extensions.*
import com.wahdanz.fixer.presentation.home.HomeViewModel
import com.wahdanz.fixer.presentation.home.FixerHomeState
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<HomeViewModel>()
    lateinit var homeAdapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        viewModel.state.observe(this, Observer {
            handelState(it)
        })
    }

    private fun handelState(it: FixerHomeState) {

        when (it) {
            is FixerHomeState.FixerData -> {
                homeAdapter.addAndReplaceAll(it.data)
                progressBar.visibility = View.GONE
            }
            is FixerHomeState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is FixerHomeState.Error -> {
                Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 1)
        homeAdapter = HomeAdapter(
            itemList = listOf(),
            layoutResIds = arrayOf(R.layout.home_item),
            bindHolder = { item ->

                item_image.setImageResource(
                    World.getFlagOf(
                        World.getAllCurrencies().firstOrNull { it.code == item.currency }?.country
                            ?: ""
                    )
                )
                amount.coloredText =
                    item.value.toString().redColor
                currency.coloredText = item.currency.blackColor
            }, itemClick = {
                startActivity(
                    DetailsActivity.startActivity(
                        context = applicationContext,
                        currency = this.currency
                    )
                )
            }
        )

        recyclerView_Fixer.adapter = homeAdapter
        recyclerView_Fixer.layoutManager = layoutManager

    }
}


