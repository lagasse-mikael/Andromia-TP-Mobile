package com.babileux.andromia.data.datasources

import com.babileux.andromia.domain.models.Explorateur
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class LoginDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun retrieve(href: String) : Explorateur {
        return withContext(Dispatchers.IO) {
            val (_, _, result) = href.httpGet().responseJson()
            when(result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString<Explorateur>(result.value.content)
                }
                is Result.Failure -> {
                    throw result.error.exception
                }
            }
        }
    }
}