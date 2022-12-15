package com.babileux.andromia.data.datasources

import com.babileux.andromia.core.Constants
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.Vault
import com.github.kittinunf.fuel.core.extensions.authentication
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

class ExplorateurDataSource {
    private val json = Json { ignoreUnknownKeys = true }


    suspend fun retriveExplorerVault(accessToken: String) : Vault {
        return withContext(Dispatchers.IO) {
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

    suspend fun setCombatCreature (accessToken: String, combatCreatureUUID: String) : Explorateur {
        return withContext(Dispatchers.IO) {
            //val combatCreature = Json.encodeToString(combatCreatureUUID)
            val (_,_, result) = Constants.BaseURL.COMBATCREATURE.httpPost().jsonBody("{\"uuid\" :\"${combatCreatureUUID}\"}").authentication().bearer(accessToken).responseJson()
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

    suspend fun payUp(accessToken: String,kernel: List<String>): Explorateur{
        return withContext(Dispatchers.IO) {
            //val combatCreature = Json.encodeToString(combatCreatureUUID)
            val (_,_, result) = Constants.BaseURL.PAYUP.httpPost().jsonBody("{\"kernel\" :\"${kernel}\"}").authentication().bearer(accessToken).responseJson()
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

    suspend fun assignCreature(accessToken: String, foundCreatureUUID: String): Explorateur{
        return withContext(Dispatchers.IO) {
            //val combatCreature = Json.encodeToString(combatCreatureUUID)
            val (_,_, result) = Constants.BaseURL.CAPTURE.httpPost().jsonBody("{\"creatureUUID\" :\"${foundCreatureUUID}\"}").authentication().bearer(accessToken).responseJson()
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