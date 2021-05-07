package com.wahdanz.fixer.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.blongho.country_data.World
import com.wahdanz.fixer.R
import com.wahdanz.fixer.extensions.updateText
import com.wahdanz.fixer.presentation.details.CurrencyConvertState
import com.wahdanz.fixer.presentation.details.CurrencyConvertViewModel
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.home_item.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsActivity : AppCompatActivity() {
    private val viewModel by viewModel<CurrencyConvertViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (intent.extras == null || (!intent.hasExtra(Currency)))
            finish()
        viewModel.convertCurrency(intent.getStringExtra(Currency), 1.0)
        viewModel.state.observe(this, Observer {
            handelState(it)
        })
        euro_edit_text.doAfterTextChanged {
            val value = it.toString()
            if (value.isNotEmpty()) {
                viewModel.convertCurrency(intent.getStringExtra(Currency), value.toDouble())
            }
        }
        currency_image.setImageResource(
            World.getFlagOf(
                World.getAllCurrencies().firstOrNull { it.code == intent.getStringExtra(Currency) }?.country
                    ?: ""
            )
        )

    }

    private fun handelState(it: CurrencyConvertState) {

        when (it) {
            is CurrencyConvertState.Update -> {
                progressBar.visibility = View.GONE
                other_edit_text.updateText(it.data.toString())
            }
            is CurrencyConvertState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is CurrencyConvertState.Error -> {
                Toast.makeText(this, it.errorMsg, Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        }
    }


    companion object {

        private const val Currency = "currency"
        fun startActivity(context: Context?, currency: String): Intent {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(Currency, currency)
            return intent
        }
    }
}

