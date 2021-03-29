package com.vk.breakingbad.ui.screen1
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.breakingbad.BreakingBadAPI
import com.vk.breakingbad.R
import com.vk.breakingbad.databinding.Screen1Binding
import com.vk.breakingbad.datasource.Character
import kotlinx.coroutines.launch
class Screen1Fragment : Fragment() {

    private lateinit var binding: Screen1Binding

    var characters: List<Character>? = null

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
        val spinner = binding.spinner
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredCharacters = characters?.filter { it.name.startsWith(newText ?: "", true) } ?: emptyList()
                val adapter = CharacterAdapter(filteredCharacters, object: CharacterAdapter.OnClickListener{
                    override fun onRegisterItemClick(id: Int) {
                        onItemClick(id)
                    }

                })
                binding.recyclerView.adapter = adapter
                return true

            }

        })

        lifecycleScope.launch {
            characters = BreakingBadAPI.RETROFIT_SERVICE.getCharacter()
            val adapter = CharacterAdapter(characters ?: emptyList(), object: CharacterAdapter.OnClickListener{
                override fun onRegisterItemClick(id: Int) {
                    onItemClick(id)
                }

            })
            binding.recyclerView.adapter = adapter

        }
    }
    fun onItemClick(id:Int){
        val action = Screen1FragmentDirections.actionScreen1FragmentToScreen2Fragment(id)
        findNavController().navigate(action)
    }
}