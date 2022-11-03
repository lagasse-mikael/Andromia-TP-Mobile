package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Exploration(
    val explorationDate: String="",
    val explorationId: Int,
    val destination: String,
    val affinity: String,
    val vault : Vault,
    val creature: Creature
)
