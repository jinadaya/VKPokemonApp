package com.example.royaal.domain.model

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
