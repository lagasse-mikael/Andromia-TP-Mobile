package com.babileux.andromia.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Explorateur(
    val username:String="",
    val email:String="",
    val password:String="",
    val inox:Int = 0,
    val element: List<Element> = listOf() ,
    val explorations: List<Exploration> = listOf()
)
