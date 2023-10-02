package com.carlos.coingecko.presentation.CoinList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.coingecko.domain.use_case.CoinListUseCase
import com.carlos.coingecko.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val coinListUseCase: CoinListUseCase
) : ViewModel(){

    private val coinListValue = MutableStateFlow(CoinListState())
    var _coinListValue : StateFlow<CoinListState> = coinListValue

    fun getAllCoins(page : String) = viewModelScope.launch(Dispatchers.IO) {
        coinListUseCase(page).collect { result ->
            when (result) {
               is ResponseState.Success -> {
                   coinListValue.value = CoinListState(coinList = result.data?: emptyList())
               }
               is ResponseState.Loading -> {
                   coinListValue.value = CoinListState(isLoading = true)
               }
               is ResponseState.Error -> {
                     coinListValue.value = CoinListState(error = result.message?: "An Unexpected Error")
               }

            }
        }
    }
}