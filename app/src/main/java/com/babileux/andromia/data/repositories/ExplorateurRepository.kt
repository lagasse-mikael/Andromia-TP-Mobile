package com.babileux.andromia.data.repositories

import com.babileux.andromia.core.Constants
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.datasources.ExplorateurDataSource
import com.babileux.andromia.domain.models.Creature
import com.babileux.andromia.domain.models.Explorateur
import com.babileux.andromia.domain.models.Vault
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.UUID


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

    suspend fun retrieveExplorerCombatCreature (accessToken: String) : Flow<LoadingResource<Creature>> {
        return flow{
            while(true) {
                try {
                    emit(LoadingResource.Loading())
                    emit(LoadingResource.Success(explorateurDataSource.retriveCombatCreature(accessToken)))
                } catch (ex:Exception) {
                    emit(LoadingResource.Error(ex))
                }
                delay(Constants.COMBATCREATUREDELAY)
            }

        }
    }

    suspend fun setCombatCreature(accessToken: String, combatCreatureUUID: String) : Resource<Explorateur> {
        return try{
            Resource.Success(explorateurDataSource.setCombatCreature(accessToken, combatCreatureUUID))
        } catch (ex:Exception) {
            Resource.Error(ex)
        }
    }

}