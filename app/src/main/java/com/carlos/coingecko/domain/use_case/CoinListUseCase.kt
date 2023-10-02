package com.carlos.coingecko.domain.use_case

import com.carlos.coingecko.data.data_source.dto.CoinDTO.CoinDTO
import com.carlos.coingecko.data.data_source.dto.CoinListDTO.CoinsListDTO
import com.carlos.coingecko.data.data_source.dto.CoinListDTO.CoinsListDTOItem
import com.carlos.coingecko.domain.model.Coin
import com.carlos.coingecko.domain.model.CoinDetail
import com.carlos.coingecko.domain.repository.CoinRepository
import com.carlos.coingecko.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinListUseCase @Inject constructor(
    private val repository: CoinRepository
){

    operator fun invoke(page: String) : Flow<ResponseState<List<Coin>>> = flow {
        try {
            emit(ResponseState.Loading<List<Coin>>())
            val coinList = repository.getAllCoins(page = page).map {
                it.toCoin()
            }
            emit(ResponseState.Success<List<Coin>>(coinList))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<Coin>>(e.localizedMessage?: "An Unexpected Error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<Coin>>("Internet Error"))
        }
    }

}