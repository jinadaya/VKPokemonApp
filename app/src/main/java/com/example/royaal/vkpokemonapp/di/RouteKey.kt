package com.example.royaal.vkpokemonapp.di

import com.example.royaal.vkpokemonapp.utils.FeatureEntry
import dagger.MapKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.TYPE)
@MapKey
annotation class RouteKey(
    val key: KClass<out FeatureEntry>
)
