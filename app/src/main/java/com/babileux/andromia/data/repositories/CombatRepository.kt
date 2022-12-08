package com.babileux.andromia.data.repositories

import android.util.Log
import com.babileux.andromia.core.Constants
import com.babileux.andromia.data.datasources.CombatDataSource
import com.babileux.andromia.data.datasources.CreatureDataSource
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

class CombatRepository {

    private val combatDataSource = CombatDataSource()


    suspend fun generateFight(enemy : Creature, token: String, username : String) {
        combatDataSource.generateFight(enemy, token, username)
    }
}

