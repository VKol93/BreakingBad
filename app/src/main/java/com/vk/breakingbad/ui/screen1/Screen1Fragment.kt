package com.vk.breakingbad.ui.screen1
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.breakingbad.BreakingBadAPI
import com.vk.breakingbad.R
import com.vk.breakingbad.databinding.Screen1Binding
import com.vk.breakingbad.datasource.Character
import com.vk.breakingbad.utils.CharactersFilters
import kotlinx.coroutines.launch
import java.lang.IllegalStateException

class Screen1Fragment : Fragment() {
    private lateinit var binding: Screen1Binding

    var characters: List<Character>? = null
    var season: String = ""
    var input: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.screen1, container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Screen1Binding.bind(view)
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        setListeners()
        refreshCharacters()
    }

    private fun setListeners() {
        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { //когда enter нажат после ввода
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                input = newText
                filterAndShowCharacters()
                return true
            }
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                season = when (position) {
                    0 -> ""
                    1 -> "1"
                    2 -> "2"
                    3 -> "3"
                    4 -> "4"
                    5 -> "5"
                    else -> throw IllegalStateException("Unknown Season")
                }
                filterAndShowCharacters()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun refreshCharacters() {
        lifecycleScope.launch {
            characters = BreakingBadAPI.RETROFIT_SERVICE.getCharacter()
            filterAndShowCharacters()
        }
    }

    private fun filterAndShowCharacters() {
        val filteredCharacters = CharactersFilters.filterByAllFilters(characters, season, input)
        val adapter = CharacterAdapter(filteredCharacters?: emptyList(), object: CharacterAdapter.OnClickListener{
            override fun onRegisterItemClick(id: Int) {
                onItemClick(id)
            }
        })
        binding.recyclerView.adapter = adapter
    }



    private fun onItemClick(id:Int){
        val action = Screen1FragmentDirections.actionScreen1FragmentToScreen2Fragment(id)
        findNavController().navigate(action)
    }
}

