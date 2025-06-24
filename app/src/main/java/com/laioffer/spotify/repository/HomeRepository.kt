package com.laioffer.spotify.repository

import com.laioffer.spotify.datamodel.Section
import com.laioffer.spotify.network.NetworkApi
import com.laioffer.spotify.network.NetworkModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject


class HomeRepository @Inject constructor (
    private val networkApi: NetworkApi
) {

    suspend fun getHomeSections(): List<Section> = withContext(Dispatchers.IO) {
        delay(3000)
        val response = networkApi.getHomeFeed().execute()
        val sections = response.body()
        sections ?: listOf()
    }
}