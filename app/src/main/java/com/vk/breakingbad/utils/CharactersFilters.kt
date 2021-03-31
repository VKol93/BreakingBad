package com.vk.breakingbad.utils

import com.vk.breakingbad.datasource.Character

object CharactersFilters {
    fun filterByAllFilters(characters: List<Character>?, season: String, input: String?): List<Character>? {
        val filteredBySeasonCharacters = filterBySeason(season, characters)
        val filteredCharacters = filterByInputName(filteredBySeasonCharacters, input)
        return filteredCharacters
    }

    fun filterByInputName(filteredBySeasonCharacters: List<Character>?, input: String?): List<Character>? {
        val filteredCharacters = filteredBySeasonCharacters?.filter {
            it.name.startsWith(input ?: "", true)
        }
        return filteredCharacters
    }

    fun filterBySeason(season: String, characters: List<Character>?): List<Character>? {
        val filteredBySeasonCharacters = if (season == "")
            characters
        else
            characters?.filter { it.season.contains(season) }
        return filteredBySeasonCharacters
    }
}