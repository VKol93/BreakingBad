package com.vk.breakingbad.ui.screen1

import androidx.lifecycle.*
import com.vk.breakingbad.BreakingBadAPI
import com.vk.breakingbad.data.Character
import com.vk.breakingbad.utils.CharactersFilters
import kotlinx.coroutines.launch

class Screen1ViewModel: ViewModel() {
    private var charactersFromApi: List<Character>? = null
    var filteredCharactersLiveData = MutableLiveData<List<Character>>()
    var season = MutableLiveData<String>("")
    var input = MutableLiveData<String>("")

    init {
        season.observeForever {
            val filteredCharacters = CharactersFilters.filterByAllFilters(charactersFromApi, it, input.value)
            filteredCharactersLiveData.value = filteredCharacters
        }

        input.observeForever {
            val filteredCharacters = CharactersFilters.filterByAllFilters(charactersFromApi, season.value, it)
            filteredCharactersLiveData.value = filteredCharacters
        }
    }

    fun refreshCharacters() {
        viewModelScope.launch {
            charactersFromApi= BreakingBadAPI.RETROFIT_SERVICE.getCharacter()
            val filteredCharacters = CharactersFilters.filterByAllFilters(charactersFromApi, season.value, input.value)
            filteredCharactersLiveData.value = filteredCharacters
        }
    }

}