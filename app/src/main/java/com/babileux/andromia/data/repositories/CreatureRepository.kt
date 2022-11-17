package com.babileux.andromia.data.repositories


import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.babileux.andromia.core.Constants
import com.babileux.andromia.core.LoadingResource

import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.datasources.CreatureDataSource
import com.babileux.andromia.data.datasources.ExplorateurDataSource
import com.babileux.andromia.data.datasources.LoginDataSource
import com.babileux.andromia.domain.models.*
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CreatureRepository {

    private val creatureDataSource = CreatureDataSource()

    suspend fun retrieveCreatureExplorer(accessToken: String) : Flow<LoadingResource<List<Creature>>> {
        return flow{
            try{
                emit(LoadingResource.Loading())
                emit(LoadingResource.Success(creatureDataSource.retrieveCreatureExplorer(accessToken)))
            } catch (ex:Exception) {
                emit(LoadingResource.Error(ex))
            }
        }
    }


}