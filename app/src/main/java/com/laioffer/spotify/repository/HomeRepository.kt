package com.laioffer.spotify.repository

import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.network.NetworkApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

// @Inject: 1. provide, 2. inject NetworkAPi
class HomeRepository @Inject constructor(
    private val networkApi: NetworkApi
) {
    suspend fun getHomeSections(): List<Section> = withContext(Dispatchers.IO) {

        val response: Response<List<Section>> = networkApi.getHomeFeed().execute()
        val sections: List<Section>? = response.body()
        sections ?: listOf()

        //networkApi.getHomeFeed().execute().body() ?: listOf()
    }
}