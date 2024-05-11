package com.example.royaal.domain

data class PokemonListStatsDTO(
    val statNames: List<String>,
    val statValues: Map<String, Int>,
    val statEfforts: Map<String, Int>,
) {
    companion object {
        val placeholder = PokemonListStatsDTO(
            emptyList(), emptyMap(), emptyMap()
        )
    }
}
