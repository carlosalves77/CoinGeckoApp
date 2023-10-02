package com.carlos.coingecko.presentation.CoinList

import com.carlos.coingecko.domain.model.Coin

data class CoinListState(
    var isLoading : Boolean = false,
    var coinList : List<Coin> = emptyList(),
    var error : String = "",
)
