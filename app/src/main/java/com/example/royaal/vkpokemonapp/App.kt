package com.example.royaal.vkpokemonapp

import android.app.Application
import com.example.royaal.vkpokemonapp.di.DaggerAppComponent

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}