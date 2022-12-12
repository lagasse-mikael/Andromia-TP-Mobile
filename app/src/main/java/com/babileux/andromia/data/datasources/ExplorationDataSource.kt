package com.babileux.andromia.data.datasources

import android.util.Log
import com.babileux.andromia.core.Constants
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.Exploration
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPatch
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.content.QRContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.math.log

class ExplorationDataSource {

    private val json = Json { ignoreUnknownKeys = true }


    suspend fun GenerateExploration(token: String, qrKey: String) {
        return withContext(Dispatchers.IO) {

            val map = mapOf("qrKey" to qrKey)
            val (request, response, result) = Constants.BaseURL.EXPLORATION.httpPost()
                .jsonBody(Json.encodeToString(map)).authentication().bearer(token).responseJson()
            Log.d("GG", request.toString())
            when (result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString(result.value.content)
                }
                is Result.Failure -> {
                    throw result.error.exception
                }
            }
        }
    }

    suspend fun retrieveAll(accesToken: String): List<Exploration> {

        return withContext(Dispatchers.IO) {
            val (_, _, result) = Constants.BaseURL.USER_EXPLORATION.httpGet().authentication()
                .bearer(accesToken).responseJson()
            when (result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString(result.value.content)
                }
                is Result.Failure -> {
                    throw result.error.exception

                }
            }
        }
    }

    suspend fun setFightResult(accessToken: String, exploration:Exploration): Exploration  {
        return withContext(Dispatchers.IO) {
            val explorationInfos = Json.encodeToString(exploration)
            val (request, response, result) = Constants.BaseURL.SETRESULT.httpPatch().jsonBody(explorationInfos).authentication()
                .bearer(accessToken).responseJson()
            Log.i(request.toString(),"le request")
            when (result) {
                is Result.Success -> {
                    return@withContext json.decodeFromString<Exploration>(result.value.content)
                }
                is Result.Failure -> {
                    throw result.error.exception

                }
            }
        }
    }
}