package com.babileux.andromia.data.datasources

import android.util.Log
import com.babileux.andromia.core.Constants
import com.babileux.andromia.domain.models.Combat
import com.babileux.andromia.domain.models.Creature
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate
import java.time.LocalDateTime

class CombatDataSource {
    private val json = Json { ignoreUnknownKeys = true }

    suspend fun generateFight(buddy : Creature,enemy : Creature,  token: String, username : String) : Combat {
        return withContext(Dispatchers.IO) {
            val current = ""
            val combat = Combat(username, buddy, enemy, current, false)
            val (request, response, result) = Constants.BaseURL.COMBAT.httpPost()
                .jsonBody(Json.encodeToString(combat)).authentication().bearer(token).responseJson()
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
}