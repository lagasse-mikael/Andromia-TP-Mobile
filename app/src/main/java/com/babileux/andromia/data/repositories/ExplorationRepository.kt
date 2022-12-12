package com.babileux.andromia.data.repositories

import com.babileux.andromia.core.Constants
import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.core.Resource
import com.babileux.andromia.data.datasources.ExplorateurDataSource
import com.babileux.andromia.data.datasources.ExplorationDataSource
import com.babileux.andromia.domain.models.Element
import com.babileux.andromia.domain.models.Exploration
import io.github.g00fy2.quickie.ScanQRCode
import io.github.g00fy2.quickie.content.QRContent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExplorationRepository {
    private val explorationDataSource = ExplorationDataSource()

    suspend fun retriveExploration(accesToken : String) : Flow<LoadingResource<List<Exploration>>> {
        return flow{
            while (true) {
                try {
                    emit(LoadingResource.Loading())
                    emit(LoadingResource.Success(explorationDataSource.retrieveAll(accesToken)))
                } catch (ex: Exception) {
                    emit(LoadingResource.Error(ex, ex.message))
                }
                delay(Constants.EXPLORATIONS_DELAY)
            }
        }
    }

    suspend fun setResult(token: String ,exploration: Exploration): Resource<Exploration> {
        return try{
            Resource.Success(explorationDataSource.setFightResult(token, exploration))
        } catch(ex:Exception){
            Resource.Error(ex,ex.message)
        }
    }



   suspend fun GenerateExploration(token: String ,qrCode: String) {
        explorationDataSource.GenerateExploration(token, qrCode)
    }
}