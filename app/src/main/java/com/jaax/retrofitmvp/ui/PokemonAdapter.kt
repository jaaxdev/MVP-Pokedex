package com.jaax.retrofitmvp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaax.retrofitmvp.utils.MainConstants
import com.jaax.retrofitmvp.R
import com.jaax.retrofitmvp.data.model.Pokemon

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {
    private var listPokemon = ArrayList<Pokemon>(0)

    inner class ViewHolder( view: View ): RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.image)
        var name: TextView = view.findViewById(R.id.name)
        var number: TextView = view.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from( parent.context )
            .inflate(R.layout.adapter_pokemon, parent, false )
        return ViewHolder( view )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = listPokemon[position]

        Glide
            .with( holder.itemView.context )
            .load( "${MainConstants.POKEMON_IMAGE_URL}${pokemon.getNumber()}.png" )
            .centerCrop()
            .into( holder.image )

        holder.name.text = pokemon.name
        holder.number.text = "#".plus(pokemon.getNumber())
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    fun addAllPokemon( list: List<Pokemon> ) {
        listPokemon.addAll(list)
        notifyDataSetChanged()
    }
}