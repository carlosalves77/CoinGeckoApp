package com.carlos.coingecko.data.repository

import com.carlos.coingecko.data.data_source.dto.CoinDTO.CoinDTO
import com.carlos.coingecko.data.data_source.dto.CoinGeckoAPI
import com.carlos.coingecko.data.data_source.dto.CoinListDTO.CoinsListDTOItem
import com.carlos.coingecko.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinGeckoAPI
) : CoinRepository{
    override suspend fun getAllCoins(page: String): List<CoinsListDTOItem> {
      return api.getAllCoins(page)
    }

    override suspend fun getCoinById(id: String): CoinDTO {
        return api.getCoinById(id)
    }


}