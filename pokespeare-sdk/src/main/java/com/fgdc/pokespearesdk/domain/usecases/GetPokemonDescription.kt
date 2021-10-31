package com.fgdc.pokespearesdk.domain.usecases

import com.fgdc.pokespearesdk.domain.model.PokemonDescription
import com.fgdc.pokespearesdk.domain.repository.PokemonRepository
import com.fgdc.pokespearesdk.utils.functional.State
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPokemonDescription @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseUseCase<State<PokemonDescription>, GetPokemonDescription.Params>() {

    override suspend fun run(params: Params?): State<PokemonDescription> {
        return pokemonRepository.getPokemonDescription(name = params!!.name)
    }

    class Params(var name: String)
}
