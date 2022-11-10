package com.babileux.andromia.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.babileux.andromia.core.Constants
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.datasources.LoginDataSource
import com.babileux.andromia.domain.models.Explorateur
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.datastore.preferences.preferencesDataStore
import com.babileux.andromia.domain.models.Token
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore by preferencesDataStore("userConnected")

class LoginRepository(private val context: Context) {
    private val loginDataSource = LoginDataSource()

    suspend fun retrieve(explorateur: Explorateur) : Resource<Explorateur> {
        return try {
            Resource.Success(loginDataSource.retrieve(explorateur))
        } catch(ex: Exception) {
            Resource.Error(ex, ex.message)
        }
    }

    object PreferencesKeys{
        val TOKEN = stringPreferencesKey("token")
        val REFRESHTOKEN = stringPreferencesKey("refreshToken")

    }

    val tokens: Flow<Token> = context.dataStore.data.map{ preferences ->
        val accessToken = preferences[PreferencesKeys.TOKEN]?: ""
        val refreshToken = preferences[PreferencesKeys.REFRESHTOKEN]?: ""


        Token(accessToken, refreshToken)

    }

    suspend fun createExplorateur(newExplorateur: Explorateur) : Resource<Explorateur> {
        return withContext(Dispatchers.IO) {

            val body = Json.encodeToString(newExplorateur)
            val (_, _, result) = Constants.BaseURL.EXPLORERS.httpPost().jsonBody(body).responseJson()


            when(result) {
                is Result.Success -> {
                    return@withContext Resource.Success(Json.decodeFromString<Explorateur>(result.value.content))
                }
                is Result.Failure -> {
                    return@withContext Resource.Error(result.error.exception)
                }
            }


        }
    }

    suspend fun save(tokens: Token) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN] = tokens.access_token
            preferences[PreferencesKeys.REFRESHTOKEN] = tokens.refresh_token

        }

    }
}