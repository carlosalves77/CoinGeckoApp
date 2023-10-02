package com.carlos.coingecko.presentation.CoinDetail

import com.carlos.coingecko.domain.model.CoinDetail

data class CoinDetailState(
    var isLoading: Boolean = false,
    var coinDetail: CoinDetail? = null,
    var error: String? = ""

)
