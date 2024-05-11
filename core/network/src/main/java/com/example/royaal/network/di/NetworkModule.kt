package com.example.royaal.network.di

import VKPokemonApp.core.network.BuildConfig
import com.example.royaal.foundation.di.AppScope
import com.example.royaal.network.PokemonApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

@Module
class NetworkModule {

    @BaseUrlQualifier
    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @AppScope
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    @Provides
    fun provideConverterFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
        return json.asConverterFactory(contentType)
    }

    @AppScope
    @Provides
    fun provideRetrofit(
        converterFactory: Converter.Factory,
        @BaseUrlQualifier
        baseUrl: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    fun providePokemonApi(
        retrofit: Retrofit
    ): PokemonApi = retrofit.create(PokemonApi::class.java)
}