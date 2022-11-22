package com.babileux.andromia.data.worker

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.babileux.andromia.core.Constants
import com.babileux.andromia.data.repositories.LoginRepository
import com.babileux.andromia.data.repositories.dataStore
import com.babileux.andromia.domain.models.Token
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.json.responseJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RefreshTokenWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {
    private val loginRepository = LoginRepository(applicationContext)
    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            val oldToken = loginRepository.userConnected.first()
            val (_, _, result) = Constants.BaseURL.TOKENS.httpPost().body(oldToken.refresh_token)
                .responseJson()
            when (result) {
                is com.github.kittinunf.result.Result.Success -> {
                    val tokens = json.decodeFromString<Token>(result.value.content)
                    applicationContext.dataStore.edit {
                        it[LoginRepository.PreferencesKeys.TOKEN] = tokens.access_token
                        it[LoginRepository.PreferencesKeys.REFRESHTOKEN] = tokens.refresh_token
                    }
                    return@withContext Result.success()
                }
                is com.github.kittinunf.result.Result.Failure -> {
                    //throw result.error.exception
                    return@withContext Result.failure()
                }
            }
        }
    }

}