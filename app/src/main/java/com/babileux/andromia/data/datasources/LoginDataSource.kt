package com.babileux.andromia.data.datasources

import com.babileux.andromia.core.Constants
import com.babileux.andromia.domain.models.Explorateur
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginDataSource {

    private val json = Json { ignoreUnknownKeys = true }

    suspend fun retrieve(explorateur: Explorateur) : Explorateur {
        return withContext(Dispatchers.IO) {
            val body = Json.encodeToString(explorateur)
            val (_, _, result) = Constants.BaseURL.LOGIN.httpPost().jsonBody(body).responseJson()
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