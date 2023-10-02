package com.carlos.coingecko.di


import com.carlos.coingecko.data.data_source.dto.CoinGeckoAPI
import com.carlos.coingecko.data.repository.CoinRepositoryImpl
import com.carlos.coingecko.domain.repository.CoinRepository
import com.carlos.coingecko.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CoinGeckoAPIModule {

    @Provides
    @Singleton
    fun provideCoinGeckoAPI(): CoinGeckoAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(CoinGeckoAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinGeckoRepository(api: CoinGeckoAPI): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}