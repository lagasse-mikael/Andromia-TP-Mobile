package com.babileux.andromia.data.datasources

import com.babileux.andromia.core.Constants
import com.babileux.andromia.domain.models.Creature
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CreatureDataSource {

    private val json = Json { ignoreUnknownKeys = true }


    suspend fun retrieveCreatureExplorer(accessToken: String) : List<Creature> {
        return withContext(Dispatchers.IO) {

            val (_, _, result) = Constants.BaseURL.CREATURES.httpGet().authentication().bearer(accessToken).responseJson()
            when(result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString(result.value.content)
                }
                is Result.Failure -> {
                    throw result.error.exception
                }
            }
        }
    }


}