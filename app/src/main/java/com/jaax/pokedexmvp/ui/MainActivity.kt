package com.jaax.pokedexmvp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaax.pokedexmvp.data.MainMVP
import com.jaax.pokedexmvp.data.model.Result
import com.jaax.pokedexmvp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainMVP.View {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ResultAdapter
    private lateinit var layoutManager: GridLayoutManager

    @Inject
    lateinit var presenter: MainMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = ResultAdapter(onPokeListener = { name -> onPokeClicked(name) })
        layoutManager = GridLayoutManager(this, 3)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = layoutManager

        binding.recyclerView.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) { presenter.getMorePokemon() }
        onScroll()
    }

    private fun onScroll() {
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val visibleItemCount = layoutManager.childCount
                val totalItems = layoutManager.itemCount
                val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (dy > 0) {
                    if (!presenter.getIsLoading()) {
                        if ((visibleItemCount + pastVisibleItem) >= totalItems) {
                            presenter.setIsLoading(true)
                            presenter.increaseOffset()
                            lifecycleScope.launch(Dispatchers.IO) {
                                presenter.getMorePokemon()
                            }
                            presenter.setIsLoading(false)
                        }
                    }
                }
            }
        })
    }

    private fun onPokeClicked(name: String) {
        PokemonDialog(name).show(supportFragmentManager, "showpokemon")
    }

    override fun showPokemon(listPokemon: List<Result>) {
        adapter.addAllPokemon(listPokemon)
    }

    override fun cancelProgressbar() {
        binding.progressbar.visibility = View.GONE
    }

    override fun showErrorMessage() {
        Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show()
    }

    override fun showUnsuccessMessage() {
        Toast.makeText(this, "Algo salió mal", Toast.LENGTH_SHORT).show()
    }
}