package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Token(
    val access_token :String="",
    val refresh_token: String=""
)
