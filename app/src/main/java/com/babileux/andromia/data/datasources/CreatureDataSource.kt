package com.babileux.andromia.data.datasources

import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class CreatureDataSource {

    private val json = Json { ignoreUnknownKeys = true }


    /*suspend fun retrieveAll() : List<Gateway> {

        return withContext(Dispatchers.IO) {
            val (_, _, result) = Constants.BaseURL.GATEWAYS.httpGet().responseJson()
            when(result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString(result.value.content)
                }
                is Result.Failure -> {
                    throw result.error.exception

                }
            }
        }
    }*/

}