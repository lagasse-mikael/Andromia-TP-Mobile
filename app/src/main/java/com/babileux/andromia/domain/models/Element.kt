package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Element(
    val name:String="",
    val symbol:String="",
    val quantity:Int
)
