package com.vk.breakingbad.utils

import com.vk.breakingbad.data.Character
import org.junit.Test

import org.junit.Assert.*
val walterWhite = Character(0, "Walter White", "","", "", listOf("1","2", "3"), emptyList())
val jessePickman = Character(0, "Jesse Pickman", "","", "", listOf("2", "5"), emptyList())
val skylerWhite = Character(0, "Skyler White", "","", "", listOf("3","5"), emptyList())
val wendyS = Character(0, "Wendy S", "","", "", listOf("4"), emptyList())

fun getFakeCharacters() = listOf(walterWhite, jessePickman, skylerWhite, wendyS)

class CharactersFiltersTest {

    @Test
    fun filterByAllFilters() {
        var actual = CharactersFilters.filterByAllFilters(getFakeCharacters(), "5", "j")
        var expected = listOf(jessePickman)
        assertEquals(expected, actual)
    }

    @Test
    fun `filterByName if input is empty`() {
        val actual = CharactersFilters.filterByInputName( getFakeCharacters(), "")
        val expected = getFakeCharacters()
        assertEquals(expected, actual)
    }

    @Test
    fun `filterByName if input is not empty`() {
        var actual = CharactersFilters.filterByInputName(getFakeCharacters(), "j")
        var expected = listOf(jessePickman)
        assertEquals(expected, actual)

        actual = CharactersFilters.filterByInputName(getFakeCharacters(), "J")
        expected = listOf(jessePickman)
        assertEquals(expected, actual)

        actual = CharactersFilters.filterByInputName(getFakeCharacters(), "w")
        expected = listOf(walterWhite, wendyS)
        assertEquals(expected, actual)
    }

    @Test
    fun `filterBySeasn if ALL SEASON select`() {
        val actual = CharactersFilters.filterBySeason("", getFakeCharacters())
        val expected = getFakeCharacters()
        assertEquals(expected, actual)
    }

    @Test
    fun `filterBySeasn if season number select`() {
        var actual = CharactersFilters.filterBySeason("3", getFakeCharacters())
        var expected = listOf(walterWhite, skylerWhite)
        assertEquals(expected, actual)

        actual = CharactersFilters.filterBySeason("1", getFakeCharacters())
        expected = listOf(walterWhite)
        assertEquals(expected, actual)

        actual = CharactersFilters.filterBySeason("5", getFakeCharacters())
        expected = listOf(jessePickman, skylerWhite)
        assertEquals(expected, actual)
    }
}

