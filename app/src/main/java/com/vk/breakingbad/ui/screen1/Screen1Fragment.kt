package com.vk.breakingbad.ui.screen1
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.breakingbad.R
import com.vk.breakingbad.databinding.Screen1Binding
import java.lang.IllegalStateException

class Screen1Fragment : Fragment() {
    private lateinit var binding: Screen1Binding
    val viewModel: Screen1ViewModel by viewModels()

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
        setListenerCharacters()
        viewModel.refreshCharacters()
    }

    private fun setListeners() {
        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { //когда enter нажат после ввода
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.input.value = newText
                //showCharacters()
                return true
            }
        })

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.season.value = when (position) {
                    0 -> ""
                    1 -> "1"
                    2 -> "2"
                    3 -> "3"
                    4 -> "4"
                    5 -> "5"
                    else -> throw IllegalStateException("Unknown Season")
                }
                //showCharacters()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
    private fun setListenerCharacters() {
        viewModel.filteredCharactersLiveData.observe(viewLifecycleOwner) {
            val adapter = CharacterAdapter(it
                    ?: emptyList(), object : CharacterAdapter.OnClickListener {
                override fun onRegisterItemClick(id: Int) {
                    onItemClick(id)
                }
            })
            binding.recyclerView.adapter = adapter
        }
    }



    private fun onItemClick(id:Int){
        val action = Screen1FragmentDirections.actionScreen1FragmentToScreen2Fragment(id)
        findNavController().navigate(action)
    }
}

