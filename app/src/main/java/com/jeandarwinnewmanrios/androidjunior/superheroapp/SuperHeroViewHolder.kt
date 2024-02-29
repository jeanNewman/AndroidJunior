package com.jeandarwinnewmanrios.androidjunior.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.jeandarwinnewmanrios.androidjunior.databinding.ItemSuperheroBinding
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.Result
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ItemSuperheroBinding.bind(view)
    fun bind(superHeroItemResponse: Result, onItemSelected: (String) -> Unit){

        binding.tvHeroName.text = superHeroItemResponse.name +" "+superHeroItemResponse.id
        Picasso.get().load(superHeroItemResponse.image.url).into(binding.ivHero)
        binding.root.setOnClickListener{
            onItemSelected(superHeroItemResponse.id)
        }
    }
}