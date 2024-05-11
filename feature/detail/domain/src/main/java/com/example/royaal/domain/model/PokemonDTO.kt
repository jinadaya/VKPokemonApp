package com.example.royaal.domain.model

data class PokemonDTO(
    val name: String,
    val id: Int,
    val defaultPoster: String?,
    val shinyPoster: String?,
    val isOwned: Boolean,
    val stats: StatsDTO,
) {
    companion object {
        val placeholder = PokemonDTO(
            name = "???",
            id = -1,
            defaultPoster = null,
            shinyPoster = null,
            isOwned = false,
            stats = StatsDTO.placeholder
        )
    }
}