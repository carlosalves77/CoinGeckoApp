package com.carlos.coingecko.domain.repository

import com.carlos.coingecko.data.data_source.dto.CoinListDTO.CoinsListDTOItem

interface CoinRepository {

    suspend fun getAllCoins(page: String): List<CoinsListDTOItem>

    suspend fun getCoinById(id: String): CoinDTO
}