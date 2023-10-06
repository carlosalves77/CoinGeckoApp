package com.carlos.coingecko.presentation.Coin

import com.carlos.coingecko.domain.model.CoinDetail

data class CoinState(
    var isLoading: Boolean = false,
    var coinDetail: CoinDetail? = null,
    var error: String? = ""
)
