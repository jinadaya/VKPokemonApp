package com.example.royaal.vkpokemonapp.database

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromStringList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toStringList(value: String) = Json.decodeFromString<List<String>>(value)

    @TypeConverter
    fun fromStringToIntMap(value: Map<String, Int>) = Json.encodeToString(value)

    @TypeConverter
    fun toStringToIntMap(value: String) = Json.decodeFromString<Map<String, Int>>(value)
}