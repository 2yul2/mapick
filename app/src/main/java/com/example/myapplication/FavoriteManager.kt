package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


object FavoriteManager {

    var favorites by mutableStateOf(listOf<FavoritePlace>())

    fun add(place: FavoritePlace) {

        if (
            favorites.none {
                it.name == place.name
            }
        ) {
            favorites = favorites + place
        }
    }

    fun remove(place: FavoritePlace) {

        favorites = favorites.filter {
            it.name != place.name
        }
    }

    fun isFavorite(place: FavoritePlace): Boolean {

        return favorites.any {
            it.name == place.name
        }
    }
}