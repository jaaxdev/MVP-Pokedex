package com.jaax.retrofitmvp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.jaax.retrofitmvp.data.PokemonMVP
import com.jaax.retrofitmvp.data.PokemonPresenter
import com.jaax.retrofitmvp.data.model.Pokemon
import com.jaax.retrofitmvp.data.network.PokemonService
import com.jaax.retrofitmvp.databinding.FragmentSinglePokemonBinding
import com.jaax.retrofitmvp.utils.MyConsts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentSinglePokemonBinding.inflate(requireActivity().layoutInflater)
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.getPokemon(name)
        }
        return AlertDialog.Builder(requireActivity()).setView(binding.root).create()
    }

    override fun updateUI(pokemon: Pokemon) {
        Log.i("jaaax", pokemon.toString())
        binding.tvNumber.text = MyConsts.number(pokemon.number)
        binding.tvName.text = MyConsts.name(pokemon.name)
        binding.tvHeight.text = MyConsts.heightToMetters(pokemon.height)
        binding.tvWeight.text = MyConsts.weightToKilograms(pokemon.weight)

        loadSprite(pokemon.sprites.frontDefault, binding.ivPokemonFront)
    }

    override fun stopProgressbar() {
        binding.progressbar.visibility = View.GONE
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