package com.vk.breakingbad.ui.screen2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.vk.breakingbad.BreakingBadAPI
import com.vk.breakingbad.R

import com.vk.breakingbad.databinding.Screen2Binding
import kotlinx.coroutines.launch

class Screen2Fragment : Fragment() {

    private val args: Screen2FragmentArgs by navArgs()

    private lateinit var binding: Screen2Binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.screen2, container, false
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = Screen2Binding.bind(view)


        lifecycleScope.launch {
            val characterList = BreakingBadAPI.RETROFIT_SERVICE.getCharacter()
            val character = characterList.find { it.id == args.id }!!
            binding.characterNameTextView.text = character.name
            Picasso.with(requireContext())
                    .load(character.image)
                    .into(binding.characterImage)
            binding.occupationTextView.text = character.occupation.joinToString(",")
            binding.nicknameTextView.text = character.nickname
            binding.seasonAppearanceTextView.text = character.season.joinToString(",")
            binding.statusTextView.text = character.status
        }

        /*binding = Screen1Binding.bind(view)
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

        }*/
    }

    }