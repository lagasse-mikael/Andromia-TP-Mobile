package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val hash:String="",
    val signature:String=""
)
