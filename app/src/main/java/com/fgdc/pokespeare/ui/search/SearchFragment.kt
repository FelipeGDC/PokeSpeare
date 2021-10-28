package com.fgdc.pokespeare.ui.search

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fgdc.pokespeare.R
import com.fgdc.pokespeare.databinding.FragmentSearchBinding
import com.fgdc.pokespeare.utils.extensions.simpleLoad
import com.fgdc.pokespeare.utils.helpers.TypeColorMapper
import com.fgdc.pokespearesdk.utils.functional.State
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        binding.apply {
            searchBtn.setOnClickListener {
                if (searchFldET.text.toString().isNotEmpty()) {
                    errorMsg.visibility = View.GONE
                    searchViewModel.getPokemonInfo(searchFldET.text.toString())
                } else {
                    errorMsg.visibility = View.VISIBLE
                    errorMsg.text = getString(R.string.search_fld_empty)
                }
            }
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    searchViewModel.pokemonDescription.collect { result ->
                        when (result.state) {
                            is State.Success -> {
                                binding.apply {
                                    errorMsg.visibility = View.GONE
                                    pokemonInfoView.visibility = View.VISIBLE
                                    pokemonInfoView.name.text = result.name
                                    pokemonInfoView.description.text = result.description
                                    pokemonInfoView.pokedexNumber.text =
                                        result.pokedexNumber.toString()
                                }
                            }
                            is State.BadRequest, is State.Error, is State.ErrorNoConnection -> {
                                binding.apply {
                                    pokemonInfoView.visibility = View.GONE
                                    errorMsg.text = getString(R.string.common_error)
                                    errorMsg.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
                launch {
                    searchViewModel.pokemonSprite.collect { result ->
                        when (result.state) {
                            is State.Success -> {
                                binding.apply {
                                    pokemonInfoView.sprite.simpleLoad(result.url, requireContext())
                                    pokemonInfoView.infoViewWrapper.background.colorFilter =
                                        PorterDuffColorFilter(
                                            TypeColorMapper(requireContext()).mapPokemonTypeToColor(
                                                result.type
                                            ),
                                            PorterDuff.Mode.SRC_ATOP
                                        )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
