package com.babileux.andromia.domain.models
import kotlinx.serialization.Serializable

@Serializable
data class UserConnected(
    val access_token: String = "",
    val refresh_token: String ="",
    val username: String ="",
    val nbInox: Int = 0,
    val location: String="",
    val combatCreatureUUID: String=""
)

