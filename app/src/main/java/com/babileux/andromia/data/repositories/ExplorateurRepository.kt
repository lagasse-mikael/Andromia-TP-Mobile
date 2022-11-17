package com.babileux.andromia.data.repositories

import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.datasources.ExplorateurDataSource
import com.babileux.andromia.domain.models.Vault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ExplorateurRepository {

    private val explorateurDataSource = ExplorateurDataSource()

    suspend fun retriveExplorerVault(accessToken: String) : Flow<LoadingResource<Vault>> {
        return flow{
            try{
                emit(LoadingResource.Loading())
                emit(LoadingResource.Success(explorateurDataSource.retriveExplorerVault(accessToken)))
            } catch (ex:Exception) {
                emit(LoadingResource.Error(ex))
            }
        }
    }


}