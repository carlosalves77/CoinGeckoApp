package com.carlos.coingecko.presentation.CoinDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlos.coingecko.domain.use_case.CoinUseCase
import com.carlos.coingecko.utils.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCase: CoinUseCase
) : ViewModel(){

    private val coinDetail = MutableStateFlow(CoinDetailState())
    var _coinDetail : StateFlow<CoinDetailState> = coinDetail

    fun getCoinDetailById(id : String) = viewModelScope.launch(Dispatchers.IO) {
        coinUseCase(id).collect { result ->
            when (result) {
                is ResponseState.Success -> {
                    coinDetail.value = CoinDetailState(coinDetail = result.data)
                }
                is ResponseState.Loading -> {
                    coinDetail.value = CoinDetailState(isLoading = true)
                }
                is ResponseState.Error -> {
                    coinDetail.value = CoinDetailState(error = result.message?: "An Unexpected Error")
                }

            }
        }
    }
}