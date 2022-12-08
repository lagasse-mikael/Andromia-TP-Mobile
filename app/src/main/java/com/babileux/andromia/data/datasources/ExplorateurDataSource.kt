package com.babileux.andromia.data.datasources

import android.util.Log
import com.babileux.andromia.core.Constants
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Vault
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ExplorateurDataSource {
    private val json = Json { ignoreUnknownKeys = true }


    suspend fun retriveExplorerVault(accessToken: String) : Vault {

        return withContext(Dispatchers.IO) {
            //Log.d("test",LoginRepository.PreferencesKeys.TOKEN.toString())

            val (_, _, result) = Constants.BaseURL.VAULT.httpGet().authentication().bearer(accessToken).responseJson()
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

    suspend fun retriveCombatCreature(accessToken: String) : Creature {
        return withContext(Dispatchers.IO) {
            val (_,_, result) = Constants.BaseURL.COMBATCREATURE.httpGet().authentication().bearer(accessToken).responseJson()
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