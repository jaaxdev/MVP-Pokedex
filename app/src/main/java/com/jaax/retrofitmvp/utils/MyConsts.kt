package com.jaax.retrofitmvp.utils

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.jaax.retrofitmvp.R
import com.jaax.retrofitmvp.data.model.Types

class MyConsts {
    companion object {
        const val POKEAPI_BASE_URL = "https://www.pokeapi.co/api/v2/"
        const val POKEMON_ENDPOINT = "pokemon"
        const val POKEMON_IMAGE_URL = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        const val LIMIT = 30
        const val TAG_LOG = "pokedex"

        fun numberAndName(number: Int, name: String) = "#$number ${name.uppercase()}"
        fun height(height: Int) = "Altura: ${height/10f} m"
        fun weight(weight: Int) = "Peso: ${weight/10f}Kg"
        fun hp(hp: Int) = "HP: $hp "
        fun attack(attack: Int) = "Ataque: $attack "
        fun defense(defense: Int) = "Defensa: $defense "
        fun spattack(spattack: Int) = "Atk. Esp.: $spattack "
        fun spdefense(spdefense: Int) = "Def. Esp.: $spdefense "
        fun speed(speed: Int) = "Velocidad: $speed "

        fun setTypes(types: List<Types>, tv1: TextView, tv2: TextView) {
            tv2.visibility = View.GONE
            when(types[0].type.name) {
                "grass" -> setBg(tv1, types[0].type.name, R.drawable.grass)
                "water" -> setBg(tv1, types[0].type.name, R.drawable.water)
                "fire" -> setBg(tv1, types[0].type.name, R.drawable.fire)
                "steel" -> setBg(tv1, types[0].type.name, R.drawable.steel)
                "bug" -> setBg(tv1, types[0].type.name, R.drawable.bug)
                "dragon" -> {
                    setBg(tv1, types[0].type.name, R.drawable.dragon)
                    tv1.setTextColor(Color.WHITE)
                }
                "electric" -> setBg(tv1, types[0].type.name, R.drawable.electric)
                "ghost" -> setBg(tv1, types[0].type.name, R.drawable.ghost)
                "fairy" -> setBg(tv1, types[0].type.name, R.drawable.fairy)
                "ice" -> setBg(tv1, types[0].type.name, R.drawable.ice)
                "fighting" -> {
                    setBg(tv1, types[0].type.name, R.drawable.fighting)
                    tv1.setTextColor(Color.WHITE)
                }
                "normal" -> setBg(tv1, types[0].type.name, R.drawable.normal)
                "psychic" -> setBg(tv1, types[0].type.name, R.drawable.psychic)
                "rock" -> {
                    setBg(tv1, types[0].type.name, R.drawable.rock)
                    tv1.setTextColor(Color.WHITE)
                }
                "ground" -> setBg(tv1, types[0].type.name, R.drawable.ground)
                "dark" -> {
                    setBg(tv1, types[0].type.name, R.drawable.dark)
                    tv1.setTextColor(Color.WHITE)
                }
                "poison" -> {
                    setBg(tv1, types[0].type.name, R.drawable.poison)
                    tv1.setTextColor(Color.WHITE)
                }
                "flying" -> setBg(tv1, types[0].type.name, R.drawable.flying)
            }
            if(types.size == 2) {
                tv2.visibility = View.VISIBLE
                when(types[1].type.name) {
                    "grass" -> setBg(tv2, types[1].type.name, R.drawable.grass)
                    "water" -> setBg(tv2, types[1].type.name, R.drawable.water)
                    "fire" -> setBg(tv2, types[1].type.name, R.drawable.fire)
                    "steel" -> setBg(tv2, types[1].type.name, R.drawable.steel)
                    "bug" -> setBg(tv2, types[1].type.name, R.drawable.bug)
                    "dragon" -> {
                        setBg(tv2, types[1].type.name, R.drawable.dragon)
                        tv2.setTextColor(Color.WHITE)
                    }
                    "electric" -> setBg(tv2, types[1].type.name, R.drawable.electric)
                    "ghost" -> setBg(tv2, types[1].type.name, R.drawable.ghost)
                    "fairy" -> setBg(tv2, types[1].type.name, R.drawable.fairy)
                    "ice" -> setBg(tv2, types[1].type.name, R.drawable.ice)
                    "fighting" -> {
                        setBg(tv2, types[1].type.name, R.drawable.fighting)
                        tv2.setTextColor(Color.WHITE)
                    }
                    "normal" -> setBg(tv2, types[1].type.name, R.drawable.normal)
                    "psychic" -> setBg(tv2, types[1].type.name, R.drawable.psychic)
                    "rock" -> {
                        setBg(tv2, types[1].type.name, R.drawable.rock)
                        tv2.setTextColor(Color.WHITE)
                    }
                    "ground" -> setBg(tv2, types[1].type.name, R.drawable.ground)
                    "dark" -> {
                        setBg(tv2, types[1].type.name, R.drawable.dark)
                        tv2.setTextColor(Color.WHITE)
                    }
                    "poison" -> {
                        setBg(tv2, types[1].type.name, R.drawable.poison)
                        tv2.setTextColor(Color.WHITE)
                    }
                    "flying" -> setBg(tv2, types[1].type.name, R.drawable.flying)
                }
            }
        }
        private fun setBg(tv: TextView, text: String, src: Int) {
            tv.setBackgroundResource(src)
            tv.text = text.uppercase()
        }
    }
}