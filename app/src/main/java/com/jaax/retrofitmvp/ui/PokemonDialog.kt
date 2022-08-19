package com.jaax.retrofitmvp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.jaax.retrofitmvp.data.PokemonMVP
import com.jaax.retrofitmvp.data.PokemonPresenter
import com.jaax.retrofitmvp.data.model.PokemonSingle
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.databinding.FragmentSinglePokemonBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDialog(private val name: String): DialogFragment(), PokemonMVP.View {
    private var _binding: FragmentSinglePokemonBinding? = null
    private val binding get() = _binding!!
    private lateinit var presenter: PokemonMVP.Presenter

    @Inject
    lateinit var service: PokemonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PokemonPresenter(this, service)
        presenter.initGetPokemon(name)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentSinglePokemonBinding.inflate(requireActivity().layoutInflater)
        return AlertDialog.Builder(requireActivity()).setView(binding.root).create()
    }

    override fun updateUI(pokemon: PokemonSingle) {
        binding.tvNumber.text = pokemon.number.toString()
        binding.tvName.text = pokemon.name
        binding.tvHeight.text = pokemon.height.toString()
        binding.tvWeight.text = pokemon.weight.toString()

        loadSprite(pokemon.sprites.frontDefault, binding.ivPokemonFrontDefault)
        loadSprite(pokemon.sprites.frontShiny, binding.ivPokemonFrontFemale)
        if( pokemon.sprites.frontFemale != null || pokemon.sprites.frontShinyFemale!= null) {
            loadSprite(pokemon.sprites.frontFemale, binding.ivPokemonFrontFemale)
            //loadSprite(pokemon.sprites.frontShinyFemale, binding.ivPokemonFrontFemale)
        } else {
            loadSprite(pokemon.sprites.frontDefault, binding.ivPokemonFrontFemale)
            //loadSprite(pokemon.sprites.frontShiny, binding.ivPokemonFrontFemale)
        }
    }

    override fun showNotSuccess(message: String) {
        Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun loadSprite(path: String?, target: ImageView) {
        Glide.with(requireContext()).load(path).centerCrop().into(target)
    }
}