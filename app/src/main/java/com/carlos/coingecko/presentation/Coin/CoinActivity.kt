package com.carlos.coingecko.presentation.Coin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.carlos.coingecko.R
import com.carlos.coingecko.databinding.ActivityCoinBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CoinActivity : AppCompatActivity() {

    private lateinit var binding : ActivityCoinBinding
    private val coinViewModel : CoinViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCoinBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent?.let {
            val coinId = it.getStringExtra("id")?:""
            if (coinId.isNotBlank()) {
            coinViewModel.getCoinDetailById(coinId.toString())
                observeCoinDetails()
            } else {
                Toast.makeText(this@CoinActivity, "We don't have any id to call", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun observeCoinDetails() {
      CoroutineScope(Dispatchers.IO).launch {
          coinViewModel._coinDetail.collectLatest {value ->
              if (value.isLoading) {
                  binding.coinProgressBar.visibility = View.GONE
              } else if (value.error!!.isNotBlank()) {
                   binding.coinProgressBar.visibility = View.GONE
                  Toast.makeText(this@CoinActivity, value.error, Toast.LENGTH_SHORT).show()
              } else {
                  binding.coinProgressBar.visibility = View.GONE
                  value.coinDetail?.let {coinDetail ->

                  Picasso.get().load(coinDetail.image).into(binding.imgCoinImageDetail)
                  binding.coinNameTxt.text = coinDetail.name
                  binding.coinPriceTxt.text = coinDetail.price.toString()
                  binding.coinLowPriceTxt.text = coinDetail.lowPrice.toString()
                  binding.coinHighPriceTxt.text = coinDetail.highPrice.toString()
                  binding.coinMarketCapTxt.text = coinDetail.market_cap
                  binding.coinPRicePercentChangeTxt.text = coinDetail.price_percent_change.toString()
                  }
              }
          }
      }
    }
}