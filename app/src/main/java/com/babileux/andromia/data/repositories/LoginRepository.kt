package com.babileux.andromia.data.repositories

import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.datasources.LoginDataSource
import com.babileux.andromia.domain.models.Explorateur

class LoginRepository {
    private val loginDataSource = LoginDataSource()

    suspend fun retrieve(href: String) : Resource<Explorateur> {
        return try {
            Resource.Success(loginDataSource.retrieve(href))
        } catch(ex: Exception) {
            Resource.Error(ex, ex.message)
        }
    }
}