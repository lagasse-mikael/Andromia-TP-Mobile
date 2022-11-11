package com.babileux.andromia.data.datasources

import com.babileux.andromia.core.Constants
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.data.repositories.LoginRepository.PreferencesKeys.TOKEN
import com.babileux.andromia.domain.models.Vault
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ExplorateurDataSource {
    private val json = Json { ignoreUnknownKeys = true }


    suspend fun retriveExplorerVault() : Vault {

        return withContext(Dispatchers.IO) {
            val (_, _, result) = Constants.BaseURL.VAULT.httpGet().authentication().bearer(LoginRepository.PreferencesKeys.TOKEN.toString()).responseJson()
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