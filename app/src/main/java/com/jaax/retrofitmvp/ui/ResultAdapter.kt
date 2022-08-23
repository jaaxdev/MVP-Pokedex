package com.jaax.retrofitmvp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jaax.retrofitmvp.R
import com.jaax.retrofitmvp.data.model.Result
import com.jaax.retrofitmvp.databinding.CardviewResultBinding
import com.jaax.retrofitmvp.utils.MyConsts

class ResultAdapter(
    private val onPokeListener: (String) -> Unit
): RecyclerView.Adapter<ResultAdapter.ViewHolder>() {
    private var listPokemon = ArrayList<Result>(0)

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = CardviewResultBinding.bind(view)

        fun bind(result: Result, onPokeListener: (String) -> Unit) {
            binding.tvName.text = result.name
            Glide
                .with(itemView.context)
                .load(MyConsts.POKEMON_IMAGE_URL.plus(result.getNumber()).plus(".png"))
                .thumbnail(Glide.with(itemView.context).load(R.drawable.loading))
                .into(binding.ivPokemon)

            itemView.setOnClickListener { onPokeListener(result.name) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.cardview_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = listPokemon[position]
        holder.bind(pokemon, onPokeListener)
    }

    override fun getItemCount(): Int {
        return listPokemon.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAllPokemon(list: List<Result>) {
        listPokemon.addAll(list)
        notifyDataSetChanged()
    }
}