package com.jaax.pokedexmvp.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.jaax.pokedexmvp.R
import com.jaax.pokedexmvp.data.PokemonMVP
import com.jaax.pokedexmvp.data.PokemonPresenter
import com.jaax.pokedexmvp.data.model.Pokemon
import com.jaax.pokedexmvp.data.network.PokemonService
import com.jaax.pokedexmvp.databinding.FragmentSinglePokemonBinding
import com.jaax.pokedexmvp.utils.MyConsts
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PokemonDialog @Inject constructor(private val name: String) : DialogFragment(), PokemonMVP.View {
    private var _binding: FragmentSinglePokemonBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var service: PokemonService
    private lateinit var presenter: PokemonMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = PokemonPresenter(this, service)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentSinglePokemonBinding.inflate(requireActivity().layoutInflater)
        lifecycleScope.launch(Dispatchers.IO) {
            presenter.getPokemon(name)
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun updateUI(pokemon: Pokemon) {
        binding.tvNumberAndName.text = MyConsts.numberAndName(pokemon.number, pokemon.name)
        binding.tvHeight.text = MyConsts.height(pokemon.height)
        binding.tvWeight.text = MyConsts.weight(pokemon.weight)
        binding.tvHp.text = MyConsts.hp(pokemon.stats[0].baseStat)
        binding.tvAttack.text = MyConsts.attack(pokemon.stats[1].baseStat)
        binding.tvDefense.text = MyConsts.defense(pokemon.stats[2].baseStat)
        binding.tvSpAttack.text = MyConsts.spattack(pokemon.stats[3].baseStat)
        binding.tvSpDefense.text = MyConsts.spdefense(pokemon.stats[4].baseStat)
        binding.tvSpeed.text = MyConsts.speed(pokemon.stats[5].baseStat)
        binding.barHp.progress = pokemon.stats[0].baseStat
        binding.barAttack.progress = pokemon.stats[1].baseStat
        binding.barDefense.progress = pokemon.stats[2].baseStat
        binding.barSpAttack.progress = pokemon.stats[3].baseStat
        binding.barSpDefense.progress = pokemon.stats[4].baseStat
        binding.barSpeed.progress = pokemon.stats[5].baseStat

        MyConsts.setTypes(pokemon.types, binding.type1, binding.type2)

        binding.swShiny.setOnClickListener {
            if(binding.swShiny.isChecked) {
                loadSprite(pokemon.sprites.frontShiny, binding.ivPokemonSprite)
            } else {
                loadSprite(pokemon.sprites.frontDefault, binding.ivPokemonSprite)
            }
        }

        loadSprite(pokemon.sprites.frontDefault, binding.ivPokemonSprite)
    }

    override fun stopProgressbar() {
        binding.swShiny.isEnabled = true
    }

    override fun showNotSuccess(message: String) {
        Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun loadSprite(path: String?, target: ImageView) {
        Glide.with(requireContext())
            .load(path)
            .thumbnail(Glide.with(requireContext()).load(R.drawable.loading))
            .into(target)
    }
}