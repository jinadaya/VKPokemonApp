package com.example.royaal.vkpokemonapp.models.domain

data class StatsDTO(
    val statNames: List<String>,
    val statValues: Map<String, Int>,
    val statEfforts: Map<String, Int>,
) {
    companion object {
        val placeholder = StatsDTO(
            emptyList(), emptyMap(), emptyMap()
        )
    }
}
