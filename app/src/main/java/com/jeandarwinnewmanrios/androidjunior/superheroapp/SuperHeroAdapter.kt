package com.jeandarwinnewmanrios.androidjunior.superheroapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jeandarwinnewmanrios.androidjunior.R
import com.jeandarwinnewmanrios.androidjunior.superheroapp.models.Result


class SuperHeroAdapter(var superHeroList:List<Result> = emptyList(),
                       private val onItemSelected:(String) -> Unit)
                        : RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(superHeroList: List<Result>){
        this.superHeroList = superHeroList
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero,parent,false)
        return SuperHeroViewHolder(view)
    }

    override fun getItemCount() = superHeroList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superHeroList[position], onItemSelected)

    }
}