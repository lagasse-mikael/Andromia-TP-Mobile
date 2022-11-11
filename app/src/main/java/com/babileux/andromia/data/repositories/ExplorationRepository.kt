package com.babileux.andromia.data.repositories

import com.babileux.andromia.core.LoadingResource
import com.babileux.andromia.data.datasources.ExplorateurDataSource
import com.babileux.andromia.data.datasources.ExplorationDataSource
import com.babileux.andromia.domain.models.Element
import com.babileux.andromia.domain.models.Exploration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExplorationRepository {
    private val explorationDataSource = ExplorationDataSource()

    suspend fun retriveExploration() : Flow<LoadingResource<List<Exploration>>> {
        return flow{}
    }
}