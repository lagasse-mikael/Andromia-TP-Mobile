package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Combat(
    val explorateurId:String="",
    val explorateurCreatureId: String="",
    val winner:String=""
)
