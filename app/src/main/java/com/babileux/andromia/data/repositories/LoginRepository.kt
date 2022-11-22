package com.babileux.andromia.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.babileux.andromia.core.Constants
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.datasources.LoginDataSource
import com.babileux.andromia.data.worker.RefreshTokenWorker
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.UserConnected
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.TimeUnit


val Context.dataStore by preferencesDataStore("userConnected")

class LoginRepository(private val context: Context) {
    private val loginDataSource = LoginDataSource()
    private val refreshTokenRequest: WorkRequest = PeriodicWorkRequestBuilder<RefreshTokenWorker>(Constants.TOKENS_DELAY, TimeUnit.MINUTES).build()


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
        val USERNAME =  stringPreferencesKey("username")
        val NBINOX =  intPreferencesKey("inox")
    }

    val userConnected: Flow<UserConnected> = context.dataStore.data.map{ preferences ->
        val accessToken = preferences[PreferencesKeys.TOKEN]?: ""
        val refreshToken = preferences[PreferencesKeys.REFRESHTOKEN]?: ""
        val username = preferences[PreferencesKeys.USERNAME]?:""
        val nbInox = preferences[PreferencesKeys.NBINOX]?:0
        UserConnected(accessToken, refreshToken, username, nbInox)
    }

    /*val user = context.dataStore.data.map{ preferences ->
       val username = preferences[PreferencesKeys.USERNAME]?:""
        val nbInox = preferences[PreferencesKeys.NBINOX]?:0
        mapOf("username" to username, "nbInox" to nbInox)
    }*/

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

    suspend fun save(tokens: UserConnected, username:String, nbInox:Int) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN] = tokens.access_token
            preferences[PreferencesKeys.REFRESHTOKEN] = tokens.refresh_token
            preferences[PreferencesKeys.USERNAME] = username
            preferences[PreferencesKeys.NBINOX] = nbInox
            startTokenWork(tokens.access_token,tokens.refresh_token)
        }
    }

    fun startTokenWork(accesToken: String, refreshToken:String) {
        WorkManager.getInstance(context).enqueue(refreshTokenRequest)
    }
}