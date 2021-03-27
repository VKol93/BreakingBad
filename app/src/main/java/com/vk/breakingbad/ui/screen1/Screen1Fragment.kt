package com.vk.breakingbad.ui.screen1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.vk.breakingbad.BreakingBadAPI
import com.vk.breakingbad.R
import com.vk.breakingbad.databinding.Screen1Binding
import kotlinx.coroutines.launch


class Screen1Fragment : Fragment() {

    private lateinit var binding: Screen1Binding

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
        val search = binding.searchEditText

        lifecycleScope.launch {
            val characters = BreakingBadAPI.RETROFIT_SERVICE.getCharacter()
            val adapter = CharacterAdapter(characters, object: CharacterAdapter.OnClickListener{
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