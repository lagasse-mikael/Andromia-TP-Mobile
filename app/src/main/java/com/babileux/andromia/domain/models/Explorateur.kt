package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Explorateur(
    val username:String="",
    val email:String="",
    val password:String="",
    val inox:Int,
    val element: List<Element> ,
    val explorations: List<Exploration>
)
