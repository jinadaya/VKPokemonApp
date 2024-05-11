package com.example.royaal.common.di

import dagger.MapKey
import kotlin.reflect.KClass

@Suppress("unused")
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD, AnnotationTarget.TYPE)
@MapKey
annotation class RouteKey(
    val key: KClass<out FeatureEntry>
)
