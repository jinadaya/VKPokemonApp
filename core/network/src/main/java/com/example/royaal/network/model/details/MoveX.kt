package com.example.royaal.network.model.details


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoveX(
    @SerialName("name")
    val name: String,
)