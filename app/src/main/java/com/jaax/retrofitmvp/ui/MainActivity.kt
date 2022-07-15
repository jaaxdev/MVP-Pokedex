package com.jaax.retrofitmvp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jaax.retrofitmvp.data.MainMVP
import com.jaax.retrofitmvp.data.MainPresenter
import com.jaax.retrofitmvp.data.model.Pokemon
import com.jaax.retrofitmvp.databinding.ActivityMainBinding
import com.jaax.retrofitmvp.utils.MainConstants
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class MainActivity : AppCompatActivity(), MainMVP.View {
    private lateinit var bind: ActivityMainBinding
    private lateinit var presenter: MainMVP.Presenter
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        bind = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView( bind.root )

        Logger.addLogAdapter(AndroidLogAdapter())

        layoutManager = GridLayoutManager( this, 3 )

        bind.recyclerView.setHasFixedSize( true )
        bind.recyclerView.layoutManager = layoutManager

        pokemonAdapter = PokemonAdapter()
        presenter = MainPresenter(this)
        bind.recyclerView.adapter = pokemonAdapter
        presenter.firstRequest()

    }

    override fun onResume() {
        super.onResume()
        bind.recyclerView.addOnScrollListener( object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Logger.i( "$dy" )
                if (dy > 0) {
                    val itemCount = layoutManager.childCount
                    val itemTotalCount = layoutManager.itemCount
                    val lastItems = layoutManager.findFirstVisibleItemPosition()
                    if (presenter.getMyLoadable()) {
                        if ((itemCount + lastItems) >= itemTotalCount) {
                            presenter.setMyLoadable(false)
                            presenter.increaseOffset(MainConstants.LIMIT)
                            presenter.getMorePokemon()
                        }
                    }
                }
            }
        })
    }

    override fun showPokemon(listPokemon: List<Pokemon>) {
        pokemonAdapter.addAllPokemon(listPokemon)
    }
}