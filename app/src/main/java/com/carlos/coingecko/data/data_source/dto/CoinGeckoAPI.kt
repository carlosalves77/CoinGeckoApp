package com.carlos.coingecko.data.data_source.dto

import com.carlos.coingecko.data.data_source.dto.CoinListDTO.CoinsListDTOItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinGeckoAPI {

    @GET("api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false&locale=en")
    suspend fun getAllCoins(@Query("page") page: String): List<CoinsListDTOItem>

    @GET("/api/v3/coins/{id}")
    suspend fun getCoinById(@Path("id") id: String) : CoinDTO
}