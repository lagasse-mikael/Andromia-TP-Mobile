package com.babileux.andromia.data.datasources

import com.babileux.andromia.core.Constants
import com.babileux.andromia.data.repositories.LoginRepository
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.content.QRContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ExplorationDataSource {

        private val json = Json { ignoreUnknownKeys = true }


suspend fun GenerateExploration(token : String, qrCode: String) {
        return withContext(Dispatchers.IO) {
                val body = "{'qrKey': ${qrCode}}"
                val (_, _, result) = Constants.BaseURL.EXPLORATION.httpPost().jsonBody(body).authentication().bearer(token).responseJson()
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