package com.vk.breakingbad.ui.screen1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.breakingbad.R
import com.vk.breakingbad.databinding.CharacterItemBinding
import com.vk.breakingbad.data.Character

class CharacterAdapter (val characters: List<Character>, val listener: OnClickListener): RecyclerView.Adapter<CharacterViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false))
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.itemView.setOnClickListener {
            listener.onRegisterItemClick(character.id)
        }
        holder.bind(character)
    }

    override fun getItemCount(): Int = characters.size

    interface OnClickListener {
        fun onRegisterItemClick(id: Int)
    }

}

class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(character: Character){
        val binding = CharacterItemBinding.bind(itemView)
        Picasso.with(itemView.context)
            .load(character.image)
            .into(binding.characterImageView)
        binding.characterNameTextView.text = character.name

    }
}

/*
class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bind(recipe: Recipe){
        val binding = RecipeItemBinding.bind(itemView)
        binding.recipeTitleTextView.text = recipe.title
        Picasso.with(itemView.context)
            .load("https://spoonacular.com/recipeImages/"+recipe.image)
            .into(itemView.recipeImageView)

        if(recipe.isInFavorite)
            binding.favoriteImageView.setImageResource(R.drawable.ic_baseline_favorite_24)

    }
}
*/
